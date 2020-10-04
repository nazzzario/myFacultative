package com.example.facultative.service;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Journal;
import com.example.facultative.entity.User;

import java.util.List;

public interface JournalService{
    void saveJournal(Journal journal);

    void saveOrUpdateJournals(Course course, User user);

    void deleteJournal(Long id);

    List<Journal> findAllUserGrades(Long id);
}
