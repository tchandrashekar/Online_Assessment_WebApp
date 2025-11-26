
package com.example.Online_Assessment.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttempt {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Long userId;
    
    @ManyToOne
    @JoinColumn(name="exam_id")
    private Exam exam;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private Integer score;
    
    
     private Integer correctCount;
    private Integer wrongCount;

    @Enumerated(EnumType.STRING)
    private AttemptStatus status; // IN_PROGRESS, SUBMITTED

    public enum AttemptStatus {
        IN_PROGRESS,
        SUBMITTED
    }
}
