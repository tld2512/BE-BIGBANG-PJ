package com.pj.controllers;

import com.pj.models.Objective;
import com.pj.models.Skill;
import com.pj.models.Syllabus;
import com.pj.services.IObjectiveService;
import com.pj.services.ISkillService;
import com.pj.services.ISyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ObjectiveController {
    @Autowired
    private IObjectiveService objectiveService;
    @Autowired
    private ISyllabusService syllabusService;
//    @Autowired
//    private ISkillService skillService;

    @GetMapping("/objective")
    public ResponseEntity<List<Objective>> getList() {
        List<Objective> objectives = objectiveService.findAll();
        return new ResponseEntity<>(objectives, HttpStatus.OK);
    }

    @PostMapping(value = "/objective/create/{syllabusID}", produces = "application/json")
    public ResponseEntity<String> addObjective(@PathVariable Long syllabusID, @RequestBody Objective objective) {
        Optional<Syllabus> syllabus = syllabusService.findByIdSyllabus(syllabusID);
        if (syllabus.isPresent()) {
            objectiveService.save(objective);
            syllabus.get().getObjectiveList().add(objective);
            syllabusService.save(syllabus.get());
            return new ResponseEntity<>("ok", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("failed", HttpStatus.OK);
    }

    @DeleteMapping("/objective/{id}")
    public ResponseEntity<Void> deleteObjective(@PathVariable Long id) {
        objectiveService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/objective/update")
    public ResponseEntity<Objective> updateObjective(@RequestBody Objective objective) {
        objectiveService.save(objective);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/objective/getSyllabusName/{id}")
//    public ResponseEntity<Syllabus> getSyllabusName(@PathVariable Long id) {
//        Syllabus syllabus = objectiveService.findById(id).getSyllabus();
//        return new ResponseEntity<>(syllabus, HttpStatus.OK);
//    }

//    @GetMapping("/objective/id/getSkillList")
//    public ResponseEntity<List<Skill>> getSkillListByObjective(@PathVariable Long id) {
//        Objective objectiveSelected = objectiveService.findById(id);
//        List<Skill> skills = skillService.findAllByObjective(objectiveSelected);
//        return new ResponseEntity<>(skills, HttpStatus.OK);
//    }

}
