package com.pj.repositories;

import com.pj.models.LearningActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningActivityRepository extends JpaRepository<LearningActivity, Long> {
}
