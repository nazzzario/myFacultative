package com.example.facultative.service;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.exceptions.CourseNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    void saveCourse(Course course);

    List<Course> findAll();

    void deleteById(Long id);

    Course findCourseById(Long id);

    void changeCourseStatus(Long id, CourseStatus courseStatus);

    Page<Course> findPaginated(int pageNo, int pageSize, String sortOrder, String sortDir);

    List<Course> findAllCourseByUserId(Long id);

    List<Course> findAllCourseByTeacherId(Long id);

    void addUserToCourse(String username, Long courseId) throws CourseNotFoundException;


}
