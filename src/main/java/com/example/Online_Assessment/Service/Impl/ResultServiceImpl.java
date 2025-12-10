
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.DTO.ResultDTO;
import com.example.Online_Assessment.Entity.AttemptAnswer;
import com.example.Online_Assessment.Entity.ExamAttempt;
import com.example.Online_Assessment.Repository.AttemptAnswerRepository;
import com.example.Online_Assessment.Repository.ExamAttemptRepository;
import com.example.Online_Assessment.Service.ResultService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService{
    
    private final ExamAttemptRepository attemptRepo;;
    private final AttemptAnswerRepository answerRepo;
    
    @Override
    public ResultDTO getAttemptResult(Long attemptId){
        
        ExamAttempt attempt=attemptRepo.findById(attemptId).orElseThrow(()-> new RuntimeException("Attempt not found"));
        
        List<AttemptAnswer> answers=answerRepo.findAll();
        
        int total=0,correct=0,wrong=0;
        
        for(AttemptAnswer a:answers){
            if(a.getAttempt().getId().equals(attemptId)){
                total++;
                if(a.getSelectedOption().equals(a.getQuestion().getCorrectAnswer())){
                    correct++;
                }
                else{
                    wrong++;
                }
            }
        }
        ResultDTO dto=new ResultDTO();
        dto.setAttemptId(attemptId);
        dto.setExamId(attempt.getExam().getId());
        dto.setUserId(attempt.getUserId());
        
        dto.setTotalQuestions(total);
        dto.setCorrect(correct);
        dto.setWrong(wrong);
        dto.setScore(correct*1);
        return dto;
    }
    
    @Override
    public int getHighestScore(Long examId){
        return attemptRepo.findAll().stream()
                .filter(a->a.getExam().getId().equals(examId))
                .mapToInt(a->a.getScore()==null?0:a.getScore())
                .max()
                .orElse(0);
    }
    
    @Override
    public double getAverageScore(Long examId){
        return attemptRepo.findAll().stream()
                .filter(a->a.getExam().getId().equals(examId))
                .mapToInt(a->a.getScore()==null?0:a.getScore())
                .average()
                .orElse(0);
    }
    
     @Override
    public List<Long> getPassedCandidates(Long examId) {
        return attemptRepo.findAll().stream()
                .filter(a -> a.getExam().getId().equals(examId))
                .filter(a -> a.getScore() != null && a.getScore() >= 5) // pass mark = 5
                .map(ExamAttempt::getUserId)
                .toList();
    }

    @Override
    public List<Long> getFailedCandidates(Long examId) {
        return attemptRepo.findAll().stream()
                .filter(a -> a.getExam().getId().equals(examId))
                .filter(a -> a.getScore() == null || a.getScore() < 5)
                .map(ExamAttempt::getUserId)
                .toList();
    }
    
    
}
