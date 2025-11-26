
package com.example.Online_Assessment.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptAnswer {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="attempt_id")
    private ExamAttempt attempt;
    
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;
    
    private String selectedOption;
    
}
