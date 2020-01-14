package com.pj.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class SyllabusForm implements Serializable {
    private Long id;
    private String name;
    private String image;
    private String description;
//    private Iterable<Long> objectiveList;

    public SyllabusForm() {
    }
}
