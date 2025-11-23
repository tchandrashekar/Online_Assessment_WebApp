
package com.example.Online_Assessment.Repository;

import com.example.Online_Assessment.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question,Long>{
    
}
