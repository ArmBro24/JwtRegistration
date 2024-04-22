package com.example.javaregistration.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String extractUserName(String token);

    String extractUserId(String token);

    String generateToken(Long userDetails);

    boolean isTokenValid(String token);
}
