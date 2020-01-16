package com.pj.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Syllabus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String image;
    private String description;

//    @JsonManagedReference
    @OneToMany(targetEntity = Objective.class)
    private List<Objective> objectiveList;

    public Syllabus() {
    }

    public Syllabus(String name, String description, List<Objective> objectiveList) {
        this.name = name;
        this.description = description;
        this.objectiveList = objectiveList;
    }
}
