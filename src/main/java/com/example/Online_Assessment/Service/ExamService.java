
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.ExamDTO;
import com.example.Online_Assessment.DTO.ExamStartDTO;
import com.example.Online_Assessment.Entity.Exam;


public interface ExamService {
    
    Exam createExam(ExamDTO examDTO);
    Exam addQuestionToExam(Long examId,Long questionId);
    Exam removeQuestionFromExam(Long emaxId,Long questionId);
    ExamStartDTO startExam(Long examId, Long userId);

}
