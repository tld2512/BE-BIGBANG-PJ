package com.pj.controllers;

import com.pj.models.LearningActivity;
import com.pj.services.impl.LearningActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LearningActivityController {
    @Autowired
    private LearningActivityService activityService;
    @GetMapping("/activity")
    public ResponseEntity<List<LearningActivity>> getList(){
        List<LearningActivity> activities = (List<LearningActivity>) activityService.findAll();
        return  new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @PostMapping("/activity/create")
    public ResponseEntity<Void> addActivity(@RequestBody LearningActivity activity){
        activityService.save(activity);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
}
