package com.example.facultative.service;

import com.example.facultative.entity.Journal;
import com.example.facultative.entity.enums.Grade;

import java.util.List;

public interface JournalService{
    void saveJournal(Journal journal);

    void saveOrUpdateJournals(Long courseId, Long userId, Grade grade);

    void deleteJournal(Long id);

    List<Journal> findAllUserGrades(Long id);
}
