package com.pj.services;

import com.pj.models.Syllabus;

import java.util.Optional;

public interface ISyllabusService extends GeneralService<Syllabus> {
    Optional<Syllabus> findByIdSyllabus(Long id);
}
