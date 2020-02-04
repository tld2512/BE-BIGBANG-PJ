package com.pj.services.impl;

import com.pj.models.LearningActivity;
import com.pj.repositories.LearningActivityRepository;
import com.pj.services.ILearningActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LearningActivityService implements ILearningActivityService {
    @Autowired
    private LearningActivityRepository learningActivityRepository;

    @Override
    public Iterable<LearningActivity> findAll() {
        return learningActivityRepository.findAll();
    }

    @Override
    public LearningActivity findById(Long id) {
        return learningActivityRepository.findById(id).get();
    }

    @Override
    public void save(LearningActivity activity) {
        learningActivityRepository.save(activity);
    }

    @Override
    public void delete(Long id) {
        learningActivityRepository.deleteById(id);
    }
}
