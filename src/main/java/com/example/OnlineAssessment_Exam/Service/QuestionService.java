
package com.example.OnlineAssessment_Exam.Service;

import com.example.OnlineAssessment_Exam.Model.Assessment;
import com.example.OnlineAssessment_Exam.Model.MCQQuestion;
import com.example.OnlineAssessment_Exam.Model.Question;
import com.example.OnlineAssessment_Exam.Model.TrueFalseQuestion;
import com.example.OnlineAssessment_Exam.Repository.AssessmentRepository;
import com.example.OnlineAssessment_Exam.Repository.MCQQuestionRepository;
import com.example.OnlineAssessment_Exam.Repository.QuestionRepository;
import com.example.OnlineAssessment_Exam.Repository.TrueFalseQuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class QuestionService {
    
     @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private MCQQuestionRepository mcqQuestionRepository;

    @Autowired
    private TrueFalseQuestionRepository trueFalseQuestionRepository;

    // Add MCQ Question to an assessment
    public MCQQuestion addMCQQuestion(Long assessmentId, MCQQuestion mcq) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        mcq.setAssessment(assessment);  // link question with assessment
        return mcqQuestionRepository.save(mcq);
    }

    // Add True/False Question to an assessment
    public TrueFalseQuestion addTrueFalseQuestion(Long assessmentId, TrueFalseQuestion tfq) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        tfq.setAssessment(assessment);
        return trueFalseQuestionRepository.save(tfq);
    }
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    // Get all questions for an assessment
    public List<Question> getQuestionsByAssessment(Long assessmentId) {
        Assessment assessment = assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        return assessment.getQuestions(); // already mapped from Assessment model
    }
    
    

    // Delete a question (MCQ or True/False)
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }
    
}
