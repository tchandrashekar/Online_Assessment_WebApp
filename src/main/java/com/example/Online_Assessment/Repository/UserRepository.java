
package com.example.Online_Assessment.Repository;

import com.example.Online_Assessment.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long>{
    
}
