package com.pj.services;

import com.pj.models.Objective;
import com.pj.models.Syllabus;

import java.util.List;
import java.util.Optional;

public interface IObjectiveService extends GeneralService<Objective> {
    List<Objective> findAll();
    List<Objective> findAllById(Iterable<Long> ids);
    Optional<Objective> findByIdObjective(Long id);
}
