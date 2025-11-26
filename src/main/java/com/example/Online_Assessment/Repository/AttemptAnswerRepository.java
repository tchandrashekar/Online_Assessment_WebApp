
package com.example.Online_Assessment.Repository;

import com.example.Online_Assessment.Entity.AttemptAnswer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer,Long>{
    List<AttemptAnswer> findByAttemptId(Long attemptId);
}
