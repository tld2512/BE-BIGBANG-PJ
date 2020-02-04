package com.pj.controllers.management;

import com.pj.models.Syllabus;
import com.pj.models.management.course.UserSyllabus;
import com.pj.services.management.IUserSyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserSyllabusController {
    @Autowired
    private IUserSyllabusService userSyllabusService;

    @GetMapping("/syllabusList/{userID}")
    public ResponseEntity<List<Syllabus>> getSyllabusList(@PathVariable Long userID) {
        List<Syllabus> syllabusList = userSyllabusService.findAllSyllabus(userID);
        return new ResponseEntity<>(syllabusList, HttpStatus.OK);
    }

    @PostMapping("/registerSyllabus/{userID}/{syllabusID}")
    public ResponseEntity<?> registerNewSyllabus(@PathVariable Long userID, @PathVariable Long syllabusID) {
        UserSyllabus userSyllabus = userSyllabusService.findByUserId(userID);
        if (userSyllabus != null){
            userSyllabusService.beginCourse(userID, syllabusID);
        }
        return new ResponseEntity<>(userSyllabus, HttpStatus.CREATED);
    }
}
