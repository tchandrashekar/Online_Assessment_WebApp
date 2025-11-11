
package com.example.OnlineAssessment_Exam.Service;

import com.example.OnlineAssessment_Exam.Model.User;
import com.example.OnlineAssessment_Exam.Repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User saveUser(User user){
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
    public  void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
}
