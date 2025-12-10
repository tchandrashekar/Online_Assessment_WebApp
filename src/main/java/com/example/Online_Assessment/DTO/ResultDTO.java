
package com.example.Online_Assessment.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    
    private Long attemptId;
    private Long examId;
    private Long userId;
    
    private int totalQuestions;
    private int correct;
    private int wrong;
    private int score;
    
}
