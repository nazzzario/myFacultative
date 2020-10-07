package com.example.facultative.service.impl;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.User;
import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.exception.CourseNotFoundException;
import com.example.facultative.repo.CourseRepository;
import com.example.facultative.repo.JournalRepository;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, JournalRepository journalRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.journalRepository = journalRepository;
    }

    @Override
    public void saveCourse(Course course) {
        course.setStatus(CourseStatus.NOT_STARTED);
        course.setLanguage(course.getLanguage());
        courseRepository.save(course);
    }


    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllBySubjectId(Long subjectId) {
        return courseRepository.findAllBySubjectId(subjectId);
    }

    @Override
    public int numberOfStudentsInCourse(String courseName) {
        return courseRepository.countStudentsByCourseName(courseName);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        journalRepository.deleteAllByCourse_Id(id);
        courseRepository.deleteById(id);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public void changeCourseStatus(Long id, CourseStatus courseStatus) {
        Course changeCourse = courseRepository.getOne(id);
        changeCourse.setStatus(courseStatus);
        courseRepository.save(changeCourse);
    }


    @Override
    public List<Course> findAllCourseByUserId(Long id) {
        return courseRepository.findAllByStudentsId(id);
    }

    @Override
    public List<Course> findAllCourseByTeacherId(Long id) {
        return courseRepository.findAllByTeacherId(id);
    }

    @Override
    @Transactional
    public void addUserToCourse(String username, Long courseId) throws CourseNotFoundException {
        User user = getUser(username);
        user.addCourse(getCourse(courseId));
        userRepository.save(getUser(username));
    }

    private Course getCourse(final Long id) throws CourseNotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + id + "not exists"));
    }

    private User getUser(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name" + username + "not found"));
    }


    @Override
    public Page<Course> findPaginated(int pageNo, int pageSize, String sortOrder, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortOrder).ascending() :
                Sort.by(sortOrder).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return courseRepository.findAllByStatus(CourseStatus.NOT_STARTED, pageable);
    }


}
