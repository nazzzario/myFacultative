package com.example.facultative.service.impl;

import com.example.facultative.entity.Course;
import com.example.facultative.entity.Journal;
import com.example.facultative.entity.User;
import com.example.facultative.repo.JournalRepository;
import com.example.facultative.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;
    private  Long id;

    @Autowired
    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public void saveJournal(Journal journal) {
        journal.setId(id);
        journalRepository.save(journal);
    }

    @Override
    public boolean findJournal(Course course, User user) {
        return false;
    }

    @Override
    public void saveOrUpdateJournals(Course course, User user) {
        Journal byCourseAndUser = journalRepository.findByCourseAndUser(course, user);
        if(byCourseAndUser != null){
            id = byCourseAndUser.getId();
        }

    }

}
