package com.pj.controllers;

import com.pj.models.*;
import com.pj.services.impl.ActivityService;
import com.pj.services.impl.LearningActivityService;
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
public class LearningActivityController {
    @Autowired
    private LearningActivityService activityService;

    @Autowired
    private ActivityService activityService2;

    @Autowired
    private SkillService skillService;

    @GetMapping("/activity")
    public ResponseEntity<List<LearningActivity>> getList(){
        List<LearningActivity> activities = (List<LearningActivity>) activityService.findAll();
        return  new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping(value = "/activity/create/{skillID}", produces = "application/json")
    public ResponseEntity<String> addObjective(@PathVariable Long skillID, @RequestBody LearningActivity learningActivity) {
        Optional<Skill> skill = skillService.findByIdSkill(skillID);
        if (skill.isPresent()) {
            activityService.save(learningActivity);
            skill.get().getLearningActivities().add(learningActivity);
            skillService.save(skill.get());
            return new ResponseEntity<>("ok", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("failed", HttpStatus.OK);
    }

    @DeleteMapping("/activity/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id){
        activityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/activity/update")
    public ResponseEntity<LearningActivity> updateActivity(@RequestBody LearningActivity activity){
        activityService.save(activity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listActivity")
    public ResponseEntity<List<Activity>> getListActivity(){
        List<Activity> activities = (List<Activity>) activityService2.findAll();
        return  new ResponseEntity<>(activities, HttpStatus.OK);
    }
}
