package com.pj.services.impl;

import com.pj.models.Objective;
import com.pj.models.Skill;
import com.pj.repositories.SkillRepository;
import com.pj.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService implements ISkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Iterable<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Long id) {
        return skillRepository.findById(id).get();
    }

    @Override
    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }

//    @Override
//    public List<Skill> findAllByObjective(Objective objective) {
//        return skillRepository.findAllByObjective(objective);
//    }
}
