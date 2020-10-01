package com.example.facultative.service.impl;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.dto.CourseDto;
import com.example.facultative.entity.enums.CourseStatus;
import com.example.facultative.repo.CourseRepository;
import com.example.facultative.service.CourseService;
import org.springframework.stereotype.Service;

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
}
