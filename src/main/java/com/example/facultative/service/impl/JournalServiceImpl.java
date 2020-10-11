package com.example.facultative.service.impl;

import com.example.facultative.entity.Journal;
import com.example.facultative.entity.enums.Grade;
import com.example.facultative.repo.CourseRepository;
import com.example.facultative.repo.JournalRepository;
import com.example.facultative.repo.UserRepository;
import com.example.facultative.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public JournalServiceImpl(JournalRepository journalRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.journalRepository = journalRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveJournal(Journal journal) {
        journalRepository.save(journal);
    }

    @Override
    public void saveOrUpdateJournals(Long courseId, Long userId, Grade grade) {
        Optional<Journal> byCourseAndUser = journalRepository.findByCourse_IdAndUser_Id(courseId, userId);
        if(byCourseAndUser.isPresent()){
            byCourseAndUser.get().setGrade(grade);
            saveJournal(byCourseAndUser.get());
        }else {
         saveJournal(Journal.builder()
                 .course(courseRepository.getOne(courseId))
                 .user(userRepository.getOne(userId))
                 .grade(grade).build());
        }
    }

    @Override
    public void deleteJournal(Long id) {
        journalRepository.deleteAllByCourse_Id(id);
    }

    @Override
    public List<Journal> findAllUserGrades(Long id) {
        return journalRepository.findAllByUser_Id(id);
    }

}
