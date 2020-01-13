package com.pj.controllers;

import com.pj.models.Objective;
import com.pj.models.Syllabus;
import com.pj.services.IObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ObjectiveController {
    @Autowired
    private IObjectiveService objectiveService;
    @GetMapping("/objective")
    public ResponseEntity<List<Objective>> getList(){
        List<Objective> objectives = objectiveService.findAll();
        return  new ResponseEntity<>(objectives, HttpStatus.OK);
    }

    @PostMapping(value = "/objective/create", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<Void> addObjective(@RequestBody Objective objective){
        objectiveService.save(objective);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/objective/{id}")
    public ResponseEntity<Void> deleteObjective(@PathVariable Long id){
        objectiveService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/objective/update")
    public ResponseEntity<Objective> updateObjective(@RequestBody Objective objective){
        objectiveService.save(objective);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/objective/getSyllabusName/{id}")
    public ResponseEntity<Syllabus> getSyllabusName(@PathVariable Long id){
         Syllabus syllabus = objectiveService.findById(id).getSyllabus();
         return new ResponseEntity<>(syllabus, HttpStatus.OK);
    }

}
