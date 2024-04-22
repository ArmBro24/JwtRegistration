package com.example.javaregistration.service.impl;

import com.example.javaregistration.DTO.UserDTO;
import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.request.SigninRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import com.example.javaregistration.entity.Role;
import com.example.javaregistration.entity.User;
import com.example.javaregistration.repository.UserRepository;
import com.example.javaregistration.service.AuthentService;
import com.example.javaregistration.service.JwtService;
import com.example.javaregistration.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthentServiceImpl implements AuthentService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthentResponse signup(SignUpRequest request){
        User user = User.builder().id(request.getId()).name(request.getName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user.getId());
        return JwtAuthentResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthentResponse signin(SigninRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password", e);
        }

        // Check if the user exists in the repository
        boolean userExists = userRepository.existsByEmail(request.getEmail());
        if (!userExists) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDTO userDTO = userService.getFromId(Long.valueOf(request.getEmail()));
        String jwt = jwtService.generateToken(userDTO.getId());

        return JwtAuthentResponse.builder().token(jwt).build();
    }

}
