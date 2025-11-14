
package com.example.OnlineAssessment_Exam.Repository;

import com.example.OnlineAssessment_Exam.Model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AssessmentRepository  extends JpaRepository<Assessment,Long>{
    
}
