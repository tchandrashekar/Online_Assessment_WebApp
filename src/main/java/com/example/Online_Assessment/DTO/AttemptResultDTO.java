
package com.example.Online_Assessment.DTO;

import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttemptResultDTO {
    
    private Long attemptId;
    private Long examId;
    private String examTitle;
    private Long userId;
    private Integer correctCount;
    private Integer wrongCount;
    private Integer totalQuestions;
    private Integer score;
    private LocalDateTime submittedAt;
    
}
