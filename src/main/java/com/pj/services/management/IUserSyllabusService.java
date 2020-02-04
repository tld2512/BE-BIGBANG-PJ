package com.pj.services.management;

import com.pj.models.Syllabus;
import com.pj.models.management.course.UserSyllabus;

import java.util.List;
import java.util.Optional;

public interface IUserSyllabusService {
    List<Syllabus> findAllSyllabus(Long userID);
    void beginCourse(Long userID, Long syllabusID);
    Optional<UserSyllabus> findById(Long id);
    UserSyllabus findByUserId(Long userID);
}
