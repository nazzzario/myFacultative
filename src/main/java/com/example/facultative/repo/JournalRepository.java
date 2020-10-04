package com.example.facultative.repo;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Journal;
import com.example.facultative.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<Journal,Long> {
    Journal findByCourseAndUser(Course course, User user);

    void deleteAllByCourse_Id(Long id);

    List<Journal> findAllByUser_Id(Long id);

}
