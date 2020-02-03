package com.pj.repositories;

import com.pj.models.Activity;
import com.pj.models.LearningActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningActivityRepository extends JpaRepository<LearningActivity, Long> {
}
