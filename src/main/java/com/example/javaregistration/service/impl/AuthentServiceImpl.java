package com.example.javaregistration.service.impl;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.request.SigninRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import com.example.javaregistration.entity.Role;
import com.example.javaregistration.entity.User;
import com.example.javaregistration.repository.UserRepository;
import com.example.javaregistration.service.AuthentService;
import com.example.javaregistration.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentServiceImpl implements AuthentService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthentResponse signup(SignUpRequest request){
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthentResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthentResponse signin(SigninRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthentResponse.builder().token(jwt).build();
    }
}
