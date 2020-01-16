package com.pj.services;

import com.pj.models.Objective;
import com.pj.models.Syllabus;

import java.util.List;

public interface IObjectiveService extends GeneralService<Objective> {
    List<Objective> findAll();
    List<Objective> findAllById(Iterable<Long> ids);
//    List<Objective> findAllBySyllabus(Syllabus syllabus);
}
