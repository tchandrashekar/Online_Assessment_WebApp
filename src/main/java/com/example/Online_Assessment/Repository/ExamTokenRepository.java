
package com.example.Online_Assessment.Repository;

import com.example.Online_Assessment.Entity.ExamToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamTokenRepository extends JpaRepository<ExamToken,Long>{
    Optional<ExamToken> findByToken(String token);
}
