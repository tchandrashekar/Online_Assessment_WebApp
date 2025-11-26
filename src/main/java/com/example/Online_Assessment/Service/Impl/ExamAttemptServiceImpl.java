
package com.example.Online_Assessment.Service.Impl;

import com.example.Online_Assessment.DTO.AttemptResultDTO;
import com.example.Online_Assessment.DTO.SaveAnswerDTO;
import com.example.Online_Assessment.Entity.AttemptAnswer;
import com.example.Online_Assessment.Entity.Exam;
import com.example.Online_Assessment.Entity.ExamAttempt;
import com.example.Online_Assessment.Entity.Question;
import com.example.Online_Assessment.Repository.AttemptAnswerRepository;
import com.example.Online_Assessment.Repository.ExamAttemptRepository;
import com.example.Online_Assessment.Repository.ExamRepository;
import com.example.Online_Assessment.Repository.QuestionRepository;
import com.example.Online_Assessment.Service.ExamAttemptService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ExamAttemptServiceImpl implements ExamAttemptService{
    
    private final ExamRepository examRepo;
    private  final ExamAttemptRepository attemptRepo;
    private final AttemptAnswerRepository answerRepo;
    private final QuestionRepository questionRepo;
    
    @Override
    public ExamAttempt startAttempt(Long examId,Long userId){
        Exam exam=examRepo.findById(examId).orElseThrow();
        
        ExamAttempt attempt=new ExamAttempt();
        attempt.setExam(exam);
        attempt.setUserId(userId);
        attempt.setStartTime(LocalDateTime.now());
        attempt.setStatus(ExamAttempt.AttemptStatus.IN_PROGRESS);
        return attemptRepo.save(attempt);
    }
    
    @Override
    public AttemptAnswer saveAnswer(SaveAnswerDTO dto){
        ExamAttempt attempt=attemptRepo.findById(dto.getAttemptId()).orElseThrow();
        Question q=questionRepo.findById(dto.getQuestionId()).orElseThrow();
        
        AttemptAnswer answer=new AttemptAnswer();
        answer.setAttempt(attempt);
        answer.setQuestion(q);
        answer.setSelectedOption(dto.getSelectedOption());
        
        return answerRepo.save(answer);
    }
    
      @Override
    @Transactional
    public AttemptResultDTO submitAndScoreAttempt(Long attemptId) {
        // fetch attempt & related data
        ExamAttempt attempt = attemptRepo.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (attempt.getStatus() == ExamAttempt.AttemptStatus.SUBMITTED) {
            // already submitted - return current stored result
            AttemptResultDTO existing = new AttemptResultDTO();
            existing.setAttemptId(attempt.getId());
            existing.setExamId(attempt.getExam().getId());
            existing.setExamTitle(attempt.getExam().getTitle());
            existing.setUserId(attempt.getUserId());
            existing.setCorrectCount(attempt.getCorrectCount() == null ? 0 : attempt.getCorrectCount());
            existing.setWrongCount(attempt.getWrongCount() == null ? 0 : attempt.getWrongCount());
            existing.setScore(attempt.getScore() == null ? 0 : attempt.getScore());
            existing.setTotalQuestions(attempt.getExam().getQuestions() == null ? 0 : attempt.getExam().getQuestions().size());
            existing.setSubmittedAt(attempt.getEndTime());
            return existing;
        }

        // fetch all saved answers for this attempt
        List<AttemptAnswer> answers = answerRepo.findByAttemptId(attemptId);

        // total questions in the exam (use actual exam.questions size)
        int totalQuestions = attempt.getExam().getQuestions() == null ? 0 : attempt.getExam().getQuestions().size();

        // evaluate
        int correct = 0;
        int wrong = 0;

        // We will compare answer.selectedOption (label like "A") with question.correctAnswer (label)
        for (AttemptAnswer a : answers) {
            Question q = a.getQuestion();
            String selected = a.getSelectedOption();
            String correctLabel = q.getCorrectAnswer();
            if (selected != null && selected.equalsIgnoreCase(correctLabel)) {
                correct++;
            } else {
                wrong++;
            }
        }

        // If some questions were not answered at all, they count as wrong (optional)
        int answeredCount = answers.size();
        int unanswered = Math.max(0, totalQuestions - answeredCount);
        wrong += unanswered;

        // compute marks per question: distribute exam.totalMarks equally
        int totalMarks = attempt.getExam().getTotalMarks() == null ? 0 : attempt.getExam().getTotalMarks();
        double marksPerQuestion = totalQuestions == 0 ? 0.0 : ((double) totalMarks) / totalQuestions;
        // compute score (rounded to int)
        int score = (int) Math.round(correct * marksPerQuestion);

        // persist results on attempt
        attempt.setCorrectCount(correct);
        attempt.setWrongCount(wrong);
        attempt.setScore(score);
        attempt.setEndTime(LocalDateTime.now());
        attempt.setStatus(ExamAttempt.AttemptStatus.SUBMITTED);

        attemptRepo.save(attempt); // transactional, but save to persist

        AttemptResultDTO result = new AttemptResultDTO();
        result.setAttemptId(attempt.getId());
        result.setExamId(attempt.getExam().getId());
        result.setExamTitle(attempt.getExam().getTitle());
        result.setUserId(attempt.getUserId());
        result.setCorrectCount(correct);
        result.setWrongCount(wrong);
        result.setTotalQuestions(totalQuestions);
        result.setScore(score);
        result.setSubmittedAt(attempt.getEndTime());

        return result;
    }
    
    
}
