
package com.example.OnlineAssessment_Exam.Repository;

import com.example.OnlineAssessment_Exam.Model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question,Long>{
    
    List<Question> findByAssessmentId(Long assessmentId);
    
}
