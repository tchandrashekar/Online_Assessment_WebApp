
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.Entity.Exam;
import com.example.Online_Assessment.Entity.ExamToken;
import com.example.Online_Assessment.Repository.ExamRepository;
import com.example.Online_Assessment.Repository.ExamTokenRepository;
import com.example.Online_Assessment.Service.ExamTokenService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamTokenServiceImpl implements ExamTokenService {
    private final ExamRepository examRepo;
    private final ExamTokenRepository tokenRepo;
    
    @Override
    public ExamToken generateToken(Long examId,Long userId){
        Exam exam=examRepo.findById(examId).orElseThrow();
        ExamToken token=new ExamToken();
        token.setExam(exam);
        token.setUserId(userId);
        token.setToken(UUID.randomUUID().toString());
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusHours(2));
        
        return tokenRepo.save(token);
    }
    
    @Override
    public boolean validateToken(String tokenStr){
        ExamToken token=tokenRepo.findByToken(tokenStr)
                .orElseThrow(()->new RuntimeException("Invalid token"));
        
        if(token.isUsed()) return false;
        if(token.getExpiresAt().isBefore(LocalDateTime.now())) return false;
        
        return true;
    }
}
