
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.Entity.ExamToken;
import com.example.Online_Assessment.Service.ExamTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class ExamTokenController {
    
    private final ExamTokenService tokenService;
    
    @PostMapping("/generate/{examId}/{userId}")
    public String generateToken(@PathVariable Long examId,@PathVariable Long userId){
        ExamToken token=tokenService.generateToken(examId,userId);
        String link="http://localhost:8080/api/exam/start-by-token?token="+token.getToken();
        return "Exam Link:"+link;
    }
    
    
}
