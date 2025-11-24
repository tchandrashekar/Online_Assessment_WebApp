
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.ExamDTO;
import com.example.Online_Assessment.Entity.Exam;
import com.example.Online_Assessment.Service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
