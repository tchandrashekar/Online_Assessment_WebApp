
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.ExamDTO;
import com.example.Online_Assessment.DTO.ExamStartDTO;
import com.example.Online_Assessment.Entity.Exam;
import com.example.Online_Assessment.Entity.ExamToken;
import com.example.Online_Assessment.Repository.ExamTokenRepository;
import com.example.Online_Assessment.Service.ExamService;
import com.example.Online_Assessment.Service.ExamTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/exam")
@RequiredArgsConstructor
public class ExamController {
    
    private final ExamService examService;
    private final ExamTokenService tokenService;
    private final ExamTokenRepository tokenRepo;
    
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
    
    @GetMapping("/start-by-token")
    public ExamStartDTO startByToken(@RequestParam String token) {

        boolean valid = tokenService.validateToken(token);
        if (!valid) throw new RuntimeException("Token invalid or expired");

        ExamToken examToken = tokenRepo.findByToken(token).orElseThrow();

        // Mark token as used (one-time)
        examToken.setUsed(true);
        tokenRepo.save(examToken);

        return examService.startExam(examToken.getExam().getId(), examToken.getUserId());
    }


}
