package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(
    origins = "*", 
    allowedHeaders = {"Content-Type", "Authorization", "X-Requested-With", "Accept", "Origin"},
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
    allowCredentials = "false" // origins "*" iken credentials genelde false olmalıdır
)
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Kullanıcı adı zaten var"));
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
        
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            // Şimdilik token yerine basit bir başarı mesajı ve kullanıcı adı dönüyoruz
            return ResponseEntity.ok(Map.of(
                "username", user.get().getUsername(),
                "role", user.get().getRole(),
                "token", "fake-jwt-token-for-now" 
            ));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Geçersiz kullanıcı adı veya şifre"));
    }
}