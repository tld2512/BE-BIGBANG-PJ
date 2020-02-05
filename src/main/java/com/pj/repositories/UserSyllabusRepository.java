package com.pj.repositories;

import com.pj.models.management.course.UserSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSyllabusRepository extends JpaRepository<UserSyllabus, Long> {
}
