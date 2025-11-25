
package com.example.Online_Assessment.DTO;

import java.util.List;
import lombok.Data;

@Data
public class ExamStartDTO {
      private Long examId;
    private String examTitle;
    private int durationMinutes;
    private String instructions;

    private List<QuestionDTO> questions;

    @Data
    public static class QuestionDTO {
        private Long id;
        private String questionText;
        private List<OptionDTO> options;
    }

    @Data
    public static class OptionDTO {
        private Long id;
        private String optionLabel;   // <-- Add this
        private String optionValue;   
    }
}
