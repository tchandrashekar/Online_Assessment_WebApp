
package com.example.Online_Assessment.DTO;

import java.util.List;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    
    private Long id;
    private String title;
    private Long categoryId;
    
    private List<OptionDTO> options;
    private String correctAnswer;
    
}
