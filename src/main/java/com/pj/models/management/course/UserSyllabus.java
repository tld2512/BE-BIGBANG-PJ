package com.pj.models.management.course;

import com.pj.models.Syllabus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class UserSyllabus {
    @Id
    private Long userID;

    @OneToMany(targetEntity = Syllabus.class)
    private List<Syllabus> syllabusList;

    private boolean status;

    public UserSyllabus() {
    }

    public boolean isSyllabusStarted() {
        return this.status;
    }
}
