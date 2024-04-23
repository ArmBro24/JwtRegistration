package com.example.javaregistration.controller;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.request.SigninRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import com.example.javaregistration.service.AuthentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthentService authentService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthentResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authentService.signin(request));
    }


    @GetMapping("/resource")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Here's your resource");
    }
}
