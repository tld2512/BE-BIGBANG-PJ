package com.pj.services.management;

import com.pj.models.Syllabus;
import com.pj.models.management.course.UserSyllabus;
import com.pj.repositories.SyllabusRepository;
import com.pj.repositories.UserSyllabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserSyllabusService implements IUserSyllabusService {

    @Autowired
    private UserSyllabusRepository userSyllabusRepository;
    @Autowired
    private SyllabusRepository syllabusRepository;

    @Override
    public List<Syllabus> findAllSyllabus(Long userID) {
        List<UserSyllabus> userSyllabusList = userSyllabusRepository.findAll();
        List<Syllabus> syllabusList = new ArrayList<>();
        for (UserSyllabus userSyllabus : userSyllabusList) {
            if (userSyllabus.getUserID() == userID) {
                syllabusList = userSyllabus.getSyllabusList();
                break;
            }
        }
        return syllabusList;
    }

    @Override
    public void beginCourse(Long userID, Long syllabusID) {
        List<UserSyllabus> userSyllabusList = userSyllabusRepository.findAll();
        for (UserSyllabus userSyllabus : userSyllabusList) {
            if (userSyllabus.getUserID() == userID) {
                if (!userSyllabus.isSyllabusStarted()) {
                    userSyllabus.getSyllabusList().add(syllabusRepository.findById(syllabusID).get());
                    userSyllabus.setStatus(true);
                }
                break;
            }
        }

    }

    @Override
    public Optional<UserSyllabus> findById(Long id) {
        return userSyllabusRepository.findById(id);
    }

    @Override
    public UserSyllabus findByUserId(Long userID) {
        List<UserSyllabus> userSyllabusList = userSyllabusRepository.findAll();
        for (UserSyllabus userSyllabus : userSyllabusList) {
            if (userSyllabus.getUserID() == userID) {
                return userSyllabus;
            }
        }
        return null;
    }


}
