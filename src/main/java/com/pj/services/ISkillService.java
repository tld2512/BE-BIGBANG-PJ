package com.pj.services;

import com.pj.models.Objective;
import com.pj.models.Skill;

import java.util.List;

public interface ISkillService extends GeneralService<Skill> {
    List<Skill> findAllByObjective(Objective objective);
}
