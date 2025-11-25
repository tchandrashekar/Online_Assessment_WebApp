
package com.example.Online_Assessment.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private Integer duration;
    private Integer totalMarks;
    
    @ManyToMany
    @JoinTable(name="exam_question",joinColumns=@JoinColumn(name="exam_id"),inverseJoinColumns=@JoinColumn(name="question_id"))
    @JsonIgnore
    private List<Question> questions;
    
    @Column(length = 1000)
    private String instructions;

}
