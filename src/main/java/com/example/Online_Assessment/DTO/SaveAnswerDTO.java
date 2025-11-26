
package com.example.Online_Assessment.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveAnswerDTO {
    private Long attemptId;
    private Long questionId;
    private String selectedOption;
}
