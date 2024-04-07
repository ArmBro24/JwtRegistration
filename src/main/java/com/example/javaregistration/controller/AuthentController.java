package com.example.javaregistration.controller;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import com.example.javaregistration.service.AuthentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentController {

    private final AuthentService authentService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthentResponse> signup(@RequestBody SignUpRequest request){
        return ResponseEntity.ok(authentService.signup(request));
    }
}
