package com.example.javaregistration.service.impl;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import com.example.javaregistration.entity.Role;
import com.example.javaregistration.entity.User;
import com.example.javaregistration.repository.UserRepository;
import com.example.javaregistration.service.AuthentService;
import com.example.javaregistration.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentServiceImpl implements AuthentService {
    private final UserRepository userRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthentResponse signup(SignUpRequest request){
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        var jwt = jwtService.generateToken(user);
        return JwtAuthentResponse.builder().token(jwt).build();
    }
}
