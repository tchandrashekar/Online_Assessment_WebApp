
package com.example.Online_Assessment.Repository;

import com.example.Online_Assessment.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{
     boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
