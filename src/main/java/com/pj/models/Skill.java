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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LearningActivity> getLearningActivities() {
        return learningActivities;
    }

    public void setLearningActivities(List<LearningActivity> learningActivities) {
        this.learningActivities = learningActivities;
    }
}
