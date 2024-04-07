package com.example.javaregistration.service;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.request.SigninRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;

public interface AuthentService {
    JwtAuthentResponse signup(SignUpRequest request);

    JwtAuthentResponse signin(SigninRequest request);
}
