package com.pj.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @JoinColumn(name = "syllabus_id")
//    private Syllabus syllabus;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "objective", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @OneToMany(targetEntity = Activity.class)
//    private List<Activity> activities;

    public Objective() {
    }
}
