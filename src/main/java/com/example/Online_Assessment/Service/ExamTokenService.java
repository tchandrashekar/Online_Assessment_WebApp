
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.Entity.ExamToken;


public interface ExamTokenService {
    ExamToken generateToken(Long examId,Long userId);
    boolean validateToken(String token);
}
