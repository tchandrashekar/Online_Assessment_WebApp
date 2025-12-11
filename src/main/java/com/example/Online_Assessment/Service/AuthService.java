
package com.example.Online_Assessment.Service;

import com.example.Online_Assessment.DTO.AuthRequestDTO;
import com.example.Online_Assessment.DTO.AuthResponseDTO;
import com.example.Online_Assessment.DTO.RegisterRequestDTO;
import com.example.Online_Assessment.Entity.User;
import com.example.Online_Assessment.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponseDTO register(RegisterRequestDTO req) {

        if(userRepository.existsByEmail(req.getEmail())) {
            return new AuthResponseDTO("Email already exists", null, null);
        }

        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole("CANDIDATE");

        userRepository.save(user);

        return new AuthResponseDTO("Registration successful", user.getId(), user.getRole());
    }

    public AuthResponseDTO login(AuthRequestDTO req) {

        User user = userRepository.findByEmail(req.getEmail())
                .orElse(null);

        if(user == null)
            return new AuthResponseDTO("User not found", null, null);

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            return new AuthResponseDTO("Invalid password", null, null);

        return new AuthResponseDTO("Login successful", user.getId(), user.getRole());
    }
}
