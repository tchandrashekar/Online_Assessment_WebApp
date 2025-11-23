
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.QuestionDTO;
import java.util.List;


public interface QuestionService {
    
    QuestionDTO addQuestion(QuestionDTO dto);
    QuestionDTO getQuestion(Long id);
    List<QuestionDTO> getAllQuestions();
    
}
