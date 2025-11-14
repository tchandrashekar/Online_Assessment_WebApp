
package com.example.OnlineAssessment_Exam.Controller;

import com.example.OnlineAssessment_Exam.Model.MCQQuestion;
import com.example.OnlineAssessment_Exam.Model.Question;
import com.example.OnlineAssessment_Exam.Model.TrueFalseQuestion;
import com.example.OnlineAssessment_Exam.Service.QuestionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    
      @Autowired
    private QuestionService questionService;

    // Add MCQ question
    @PostMapping("/mcq/{assessmentId}")
    public ResponseEntity<MCQQuestion> addMCQ(
            @PathVariable Long assessmentId,
            @RequestBody MCQQuestion mcq) {

        MCQQuestion saved = questionService.addMCQQuestion(assessmentId, mcq);
        return ResponseEntity.ok(saved);
    }

    // Add True/False question
    @PostMapping("/truefalse/{assessmentId}")
    public ResponseEntity<TrueFalseQuestion> addTF(
            @PathVariable Long assessmentId,
            @RequestBody TrueFalseQuestion tfq) {

        TrueFalseQuestion saved = questionService.addTrueFalseQuestion(assessmentId, tfq);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<Question>> getQuestionsByAssessment(@PathVariable Long assessmentId) {
        return ResponseEntity.ok(questionService.getQuestionsByAssessment(assessmentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
    
}
