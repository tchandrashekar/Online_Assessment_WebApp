
package com.example.OnlineAssessment_Exam.Controller;

import com.example.OnlineAssessment_Exam.Model.Assessment;
import com.example.OnlineAssessment_Exam.Service.AssessmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {
    
    @Autowired
    private AssessmentService assessmentService;
    
    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@RequestBody Assessment assessment){
        Assessment savedAssessment=assessmentService.createAssessment(assessment);
        return ResponseEntity.ok(savedAssessment);
    }
    
    
    @GetMapping
    public ResponseEntity<List<Assessment>> getAllAssessments() {
        return ResponseEntity.ok(assessmentService.getAllAssessments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getAssessmentById(@PathVariable Long id) {
        Assessment assessment = assessmentService.getAssessmentById(id);
        return ResponseEntity.ok(assessment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return ResponseEntity.noContent().build();
    }
    
}
