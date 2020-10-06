package com.example.facultative.repo;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.enums.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    List<Course> findAllByStudentsId(Long id);

    Page<Course> findAllByStatus(CourseStatus courseStatus, Pageable pageable);

    List<Course> findAllByTeacherId(Long teacherId);

    List<Course> findAllBySubjectId(Long subjectId);


}
