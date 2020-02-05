package com.pj.services;

import com.pj.models.Skill;
import java.util.Optional;

public interface ISkillService extends GeneralService<Skill> {
    Optional<Skill> findByIdSkill(Long id);}
