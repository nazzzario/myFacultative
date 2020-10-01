package com.example.facultative.service.impl;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.User;
import com.example.facultative.entity.dto.CourseDto;
import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.repo.CourseRepository;
import com.example.facultative.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveCourse(CourseDto courseDto) {
        Course course = Course.builder()
                .courseName(courseDto.getCourseName())
                .startDate(courseDto.getStartDate())
                .courseLanguage(courseDto.getLanguage())
                .description(courseDto.getDescription())
                .endDate(courseDto.getEndDate())
                .teacher(courseDto.getTeacher())
                .subject(courseDto.getSubject())
                .status(CourseStatus.NOT_STARTED)
                .build();
        courseRepository.save(course);
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course findCourseById(Long id) {
        return courseRepository.getOne(id) ;
    }

    @Override
    public void changeCourseStatus(Long id, CourseStatus courseStatus) {
        Course changeCourse = courseRepository.getOne(id);
        changeCourse.setStatus(courseStatus);
        courseRepository.save(changeCourse);
    }
}
