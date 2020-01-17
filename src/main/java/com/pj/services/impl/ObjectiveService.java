package com.pj.services.impl;

import com.pj.models.Objective;
import com.pj.models.Syllabus;
import com.pj.repositories.ObjectiveRepository;
import com.pj.services.IObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObjectiveService implements IObjectiveService {
    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Override
    public List<Objective> findAll() {
        return objectiveRepository.findAll();
    }

    @Override
    public List<Objective> findAllById(Iterable<Long> ids) {
        return objectiveRepository.findAllById(ids);
    }

    @Override
    public Optional<Objective> findByIdObjective(Long id) {
        return objectiveRepository.findById(id);
    }

    @Override
    public Objective findById(Long id) {
        return objectiveRepository.findById(id).get();
    }

    @Override
    public void save(Objective objective) {
        objectiveRepository.save(objective);
    }

    @Override
    public void delete(Long id) {
        objectiveRepository.deleteById(id);
    }
}
