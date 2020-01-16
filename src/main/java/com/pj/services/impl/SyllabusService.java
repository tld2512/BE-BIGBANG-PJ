package com.pj.services.impl;

import com.pj.models.Syllabus;
import com.pj.repositories.SyllabusRepository;
import com.pj.services.ISyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SyllabusService implements ISyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Override
    public Iterable<Syllabus> findAll() {
        return syllabusRepository.findAll();
    }

    @Override
    public Syllabus findById(Long id) {
        return syllabusRepository.findById(id).get();
    }

    @Override
    public void save(Syllabus syllabus) {
        syllabusRepository.save(syllabus);
    }

    @Override
    public void delete(Long id) {
        syllabusRepository.deleteById(id);
    }

    @Override
    public Optional<Syllabus> findByIdSyllabus(Long id) {
        return syllabusRepository.findById(id);
    }
}
