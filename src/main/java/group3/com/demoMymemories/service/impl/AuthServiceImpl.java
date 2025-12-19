package group3.com.demoMymemories.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import group3.com.demoMymemories.dto.LoginRequest;
import group3.com.demoMymemories.dto.RegisterRequest;
import group3.com.demoMymemories.entity.User;
import group3.com.demoMymemories.repository.UserRepository;
import group3.com.demoMymemories.security.JwtProvider;
import group3.com.demoMymemories.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

//    public AuthServiceImpl(UserRepository userRepository,
//                           PasswordEncoder passwordEncoder,
//                           JwtProvider jwtProvider) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtProvider = jwtProvider;
//    }

    @Override
    public String register(RegisterRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return jwtProvider.generateToken(user.getEmail());
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtProvider.generateToken(user.getEmail());
    }
}
