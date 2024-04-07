package com.example.javaregistration.controller;

import com.example.javaregistration.dao.request.SignUpRequest;
import com.example.javaregistration.dao.request.SigninRequest;
import com.example.javaregistration.dao.response.JwtAuthentResponse;
import com.example.javaregistration.service.AuthentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AuthorController {

    private final AuthentService authentService;

    public AuthorController(AuthentService authentService) {
        this.authentService = authentService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthentResponse> signin(@RequestBody SigninRequest request){
        return ResponseEntity.ok(authentService.signin(request));
    }

    @GetMapping("/resource")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Here's your resource");
    }
}
