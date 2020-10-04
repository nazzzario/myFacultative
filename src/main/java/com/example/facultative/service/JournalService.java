package com.example.facultative.service;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Journal;
import com.example.facultative.entity.User;

public interface JournalService{
    void saveJournal(Journal journal);

    boolean findJournal(Course course,  User user);

    void saveOrUpdateJournals(Course course, User user);

}
