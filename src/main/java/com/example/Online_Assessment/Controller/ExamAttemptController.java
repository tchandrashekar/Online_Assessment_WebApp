
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.AttemptResultDTO;
import com.example.Online_Assessment.DTO.SaveAnswerDTO;
import com.example.Online_Assessment.Entity.AttemptAnswer;
import com.example.Online_Assessment.Entity.ExamAttempt;
import com.example.Online_Assessment.Service.ExamAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attempt")
@RequiredArgsConstructor
public class ExamAttemptController {
    
    private final ExamAttemptService attemptService;
    
    @PostMapping("/start/{examId}/{userId}")
    public ExamAttempt startAttempt(@PathVariable Long examId,@PathVariable Long userId){
        return attemptService.startAttempt(examId,userId);
    }
    
    @PostMapping("/save-answer")
    public AttemptAnswer saveAnswer(@RequestBody SaveAnswerDTO dto){
        return attemptService.saveAnswer(dto);
    }
    
     @PostMapping("/submit/{attemptId}")
    public AttemptResultDTO submitAttempt(@PathVariable Long attemptId) {
        return attemptService.submitAndScoreAttempt(attemptId);
    }
    
}
