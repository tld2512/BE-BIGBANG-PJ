package com.pj.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pj.models.Objective;
import com.pj.models.Syllabus;
import com.pj.models.SyllabusForm;
import com.pj.services.IObjectiveService;
import com.pj.services.ISyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SyllabusController {
    @Autowired
    private ISyllabusService syllabusService;
    @Autowired
    private IObjectiveService objectiveService;
    @Autowired
    Environment env;

    @GetMapping("/syllabus")
    public ResponseEntity<List<Syllabus>> getList() {
        List<Syllabus> syllabusList = (List<Syllabus>) syllabusService.findAll();
        return new ResponseEntity<>(syllabusList, HttpStatus.OK);
    }

    @PostMapping("/syllabus/create")
    public ResponseEntity<Syllabus> addSyllabus(@RequestParam("syllabusInfo") String syllabusForm, @RequestParam("image") MultipartFile file) throws JsonParseException, JsonMappingException, IOException {
        SyllabusForm syllabusForm1 = new ObjectMapper().readValue(syllabusForm, SyllabusForm.class);
        Syllabus syllabus = new Syllabus();
        saveSyllabusFromForm(syllabus, syllabusForm1);
        String fileName = file.getOriginalFilename();
        String fileUpload = env.getProperty("uploadPath");
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String image = "http://localhost:8080/image/" + fileName;
        syllabus.setImage(image);
        syllabusService.save(syllabus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/syllabus/update")
    public ResponseEntity<Syllabus> updateSyllabus(@RequestParam("syllabusInfo") String syllabusForm, @RequestParam("image") Optional<MultipartFile> file) throws JsonProcessingException {
        SyllabusForm syllabusForm1 = new ObjectMapper().readValue(syllabusForm, SyllabusForm.class);
//        System.out.println(syllabusForm1);
        Syllabus syllabus = syllabusService.findById(syllabusForm1.getId());
        saveSyllabusFromForm(syllabus, syllabusForm1);
        if (file.isPresent()) {
            String fileName = file.get().getOriginalFilename();
            String fileUpload = env.getProperty("uploadPath");
            try {
                FileCopyUtils.copy(file.get().getBytes(), new File(fileUpload + fileName));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String image = "http://localhost:8080/image/" + fileName;
            syllabus.setImage(image);
        }
        syllabusService.save(syllabus);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/syllabus/{id}")
    public ResponseEntity<Void> deleteSyllabus(@PathVariable Long id) {
        syllabusService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/syllabus/{id}/objectiveList")
    public ResponseEntity<List<Objective>> getObjListBySyllabus(@PathVariable Long id) {
        Syllabus syllabusSelected = syllabusService.findById(id);
        List<Objective> objectiveList = objectiveService.findAllBySyllabus(syllabusSelected);
        return new ResponseEntity<>(objectiveList, HttpStatus.OK);
    }

    private void saveSyllabusFromForm(Syllabus syllabus, SyllabusForm syllabusForm) {
//        List<Objective> objectives = objectiveService.findAllById(syllabusForm.getObjectiveList());
        if (!Objects.isNull(syllabusForm.getId())) {
            syllabus.setId(syllabusForm.getId());
        }
        syllabus.setName(syllabusForm.getName());
        syllabus.setDescription(syllabusForm.getDescription());
//        syllabus.setObjectiveList(objectives);
    }
}
