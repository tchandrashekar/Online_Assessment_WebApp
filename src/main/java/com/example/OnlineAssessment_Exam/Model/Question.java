
package com.example.OnlineAssessment_Exam.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_Type")
public abstract class Question {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    
    @ManyToOne
    @JoinColumn(name="assessment_id")
    private Assessment assessment;
    public abstract String getType();
    
}
