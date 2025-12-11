
package com.example.Online_Assessment.Controller;

import com.example.Online_Assessment.DTO.AuthRequestDTO;
import com.example.Online_Assessment.DTO.AuthResponseDTO;
import com.example.Online_Assessment.DTO.LoginDTO;
import com.example.Online_Assessment.DTO.RegisterRequestDTO;
import com.example.Online_Assessment.Repository.UserRepository;
import com.example.Online_Assessment.Security.JwtUtil;
import com.example.Online_Assessment.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO req) {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto) {

        var user = userRepo.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }
}
