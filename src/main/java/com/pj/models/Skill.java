package com.pj.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private boolean status;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "objective_id")
    private Objective objective;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Activity activity;

    public Skill() {
    }
}
