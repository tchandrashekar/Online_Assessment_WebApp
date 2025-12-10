
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.ResultDTO;
import com.example.Online_Assessment.Service.ResultService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/result")
@RequiredArgsConstructor
public class ResultController {
    
    private final ResultService resultService;

    @GetMapping("/{attemptId}")
    public ResultDTO getAttemptResult(@PathVariable Long attemptId) {
        return resultService.getAttemptResult(attemptId);
    }

    @GetMapping("/highest-score/{examId}")
    public int getHighestScore(@PathVariable Long examId) {
        return resultService.getHighestScore(examId);
    }

    @GetMapping("/average-score/{examId}")
    public double getAverageScore(@PathVariable Long examId) {
        return resultService.getAverageScore(examId);
    }

    @GetMapping("/passed/{examId}")
    public List<Long> getPassedCandidates(@PathVariable Long examId) {
        return resultService.getPassedCandidates(examId);
    }

    @GetMapping("/failed/{examId}")
    public List<Long> getFailedCandidates(@PathVariable Long examId) {
        return resultService.getFailedCandidates(examId);
    }
}
