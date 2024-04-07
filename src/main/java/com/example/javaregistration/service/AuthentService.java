package com.example.javaregistration.service;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface AuthentService {
    JwtAuthentResponse signup(SignUpRequest request);
}
