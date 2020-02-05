package com.pj.controllers;

import com.pj.models.LearningActivity;
import com.pj.models.Objective;
import com.pj.models.Skill;
import com.pj.models.Syllabus;
import com.pj.services.IObjectiveService;
import com.pj.services.impl.ObjectiveService;
import com.pj.services.impl.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SkillController {
    @Autowired
    private SkillService skillService;
    @Autowired
    private IObjectiveService objectiveService;

    @GetMapping("/skill")
    public ResponseEntity<List<Skill>> getList() {
        List<Skill> skills = (List<Skill>) skillService.findAll();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @PostMapping(value = "/skill/create/{objectiveID}", produces = "application/json")
    public ResponseEntity<String> addObjective(@PathVariable Long objectiveID, @RequestBody Skill skill) {
        Optional<Objective> objective = objectiveService.findByIdObjective(objectiveID);
        if (objective.isPresent()) {
            skillService.save(skill);
            objective.get().getSkills().add(skill);
//            skill.getObjective()
            objectiveService.save(objective.get());
            return new ResponseEntity<>("ok", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("failed", HttpStatus.OK);
    }

    @DeleteMapping("/skill/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        skillService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/skill/update")
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
        skillService.save(skill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/skill/{id}/learningActivityList")
    public ResponseEntity<List<LearningActivity>> getLearning(@PathVariable Long id) {
        Skill skillSelected = skillService.findById(id);
        List<LearningActivity> learningActivityList = skillSelected.getLearningActivities();
        return new ResponseEntity<>(learningActivityList, HttpStatus.OK);
    }

//    @GetMapping("/skill/id/")
}
