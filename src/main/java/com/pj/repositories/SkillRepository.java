package com.pj.repositories;

import com.pj.models.Objective;
import com.pj.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findAllByObjective(Objective objective);
}
