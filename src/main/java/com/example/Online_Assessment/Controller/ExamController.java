
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.ExamDTO;
import com.example.Online_Assessment.DTO.ExamStartDTO;
import com.example.Online_Assessment.Entity.Exam;
import com.example.Online_Assessment.Service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    
    private final ExamService examService;
    
    @PostMapping("/create")
    public Exam createExam(@RequestBody ExamDTO dto){
        return examService.createExam(dto);
    }
    
    @PostMapping("/{examId}/add-question/{questionId}")
    public Exam addQuestionToExam(@PathVariable Long examId,@PathVariable Long questionId){
        return examService.addQuestionToExam(examId,questionId);
    }
    
    @DeleteMapping("/{examId}/remove-question/{questionId}")
    public Exam removeQuestionFromExam(@PathVariable Long examId,@PathVariable Long questionId){
        return examService.removeQuestionFromExam(examId,questionId);
    }
    
    @GetMapping("/start/{examId}/{userId}")
public ResponseEntity<ExamStartDTO> startExam(
        @PathVariable Long examId,
        @PathVariable Long userId) {

    return ResponseEntity.ok(examService.startExam(examId, userId));
}

}
