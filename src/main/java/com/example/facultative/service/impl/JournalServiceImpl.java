package com.example.facultative.service.impl;

import com.example.facultative.entity.Journal;
import com.example.facultative.entity.dto.JournalDto;
import com.example.facultative.repo.JournalRepository;
import com.example.facultative.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    @Autowired
    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public void saveJournal(JournalDto journalDto) {
        Journal build = Journal.builder()
                .course(journalDto.getCourse())
                .user(journalDto.getUser())
                .grade(journalDto.getGrade()).build();
        journalRepository.save(build);
    }
}
