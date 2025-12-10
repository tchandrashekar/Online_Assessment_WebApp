
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.ResultDTO;
import java.util.List;


public interface ResultService {
    
    ResultDTO getAttemptResult(Long attemptId);
    
    int getHighestScore(Long examId);
    double getAverageScore(Long examId);
    
    List<Long> getPassedCandidates(Long examId);
    List<Long> getFailedCandidates(Long examId);
}
