
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.AttemptResultDTO;
import com.example.Online_Assessment.DTO.SaveAnswerDTO;
import com.example.Online_Assessment.Entity.AttemptAnswer;
import com.example.Online_Assessment.Entity.ExamAttempt;


public interface ExamAttemptService {
    
    ExamAttempt startAttempt(Long examId,Long userId);
    AttemptAnswer saveAnswer(SaveAnswerDTO dto);
    AttemptResultDTO submitAndScoreAttempt(Long attemptId);
}
