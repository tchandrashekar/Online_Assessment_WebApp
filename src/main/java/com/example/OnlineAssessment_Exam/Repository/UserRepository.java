
package com.example.OnlineAssessment_Exam.Repository;

import com.example.OnlineAssessment_Exam.Model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email);
    //Optional<User> findByUsername(String name);

    boolean existsByEmail(String Email);
    
}
