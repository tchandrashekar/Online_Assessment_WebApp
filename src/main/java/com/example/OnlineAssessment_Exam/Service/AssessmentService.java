
package com.example.OnlineAssessment_Exam.Service;

import com.example.OnlineAssessment_Exam.Model.Assessment;
import com.example.OnlineAssessment_Exam.Model.Question;
import com.example.OnlineAssessment_Exam.Repository.AssessmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AssessmentService {
    
    @Autowired
    private AssessmentRepository assessmentRepository;
    
    public Assessment createAssessment(Assessment assessment){
        return assessmentRepository.save(assessment);
    }
    
    public List<Assessment> getAllAssessments(){
        return assessmentRepository.findAll();
    }
    
    public Assessment getAssessmentById(Long id){
        return assessmentRepository.findById(id).orElseThrow(()->new RuntimeException("Assessment not found with id "+id));
    }
    
    public Assessment updateAssessment(Long id, Assessment updatedAssessment) {
        Assessment existing = getAssessmentById(id);

        existing.setTitle(updatedAssessment.getTitle());
        existing.setDescription(updatedAssessment.getDescription());

        return assessmentRepository.save(existing);
    }
    
    
    public Assessment addQuestionToAssessment(Long assessmentId, Question question) {
        Assessment assessment = getAssessmentById(assessmentId);

        question.setAssessment(assessment);    // link question to assessment
        assessment.getQuestions().add(question); // add question to list

        return assessmentRepository.save(assessment);
    }
    public void deleteAssessment(Long id){
        assessmentRepository.deleteById(id);
    }
    
}
