package com.pj.repositories;

import com.pj.models.Objective;
import com.pj.models.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

//    List<Objective> getObjectivesBy
}
