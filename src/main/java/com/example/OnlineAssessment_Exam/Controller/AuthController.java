
package com.example.OnlineAssessment_Exam.Controller;

import com.example.OnlineAssessment_Exam.Config.JwtUtil;
import com.example.OnlineAssessment_Exam.DTO.LoginRequest;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public Map<String,String> login(@RequestBody LoginRequest loginRequest){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        
         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         
        String token=jwtUtil.generateToken(userDetails);
        
        Map<String,String> response =new HashMap<>();
        response.put("token",token);
        response.put("message","Login succcessful");
        return response;
    }
    
}
