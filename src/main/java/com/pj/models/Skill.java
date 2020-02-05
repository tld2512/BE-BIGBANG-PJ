package com.pj.models;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
//    private boolean status;

    @OneToMany(targetEntity = LearningActivity.class)
    private List<LearningActivity> learningActivities;

    public Skill() {
    }
}
