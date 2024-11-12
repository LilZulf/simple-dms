/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kolektifhost.simple_dms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kolektifhost.simple_dms.dto.LoginRequest;
import com.kolektifhost.simple_dms.dto.RegisterRequest;
import com.kolektifhost.simple_dms.dto.ResponseData;
import com.kolektifhost.simple_dms.service.AuthService;

import jakarta.validation.Valid;

/**
 *
 * @author najib
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Handle login request
     * 
     * @param loginRequest the body of request
     * @return ResponseData with status and message
     */
    @PostMapping("/login")
    public ResponseEntity<ResponseData<String>> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new ResponseData<>(true, "Login successful", 200, token));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }

    /**
     * Handle registration request
     * 
     * @param registerRequest the body of request containing username, password, and email
     * @return ResponseData with status, message, and token if registration is successful
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseData<String>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            String token = authService.register(registerRequest.getUsername(), registerRequest.getPassword(), registerRequest.getEmail());
            return ResponseEntity.ok(new ResponseData<>(true, "Registration successful", 200, token));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseData<>(false, e.getMessage(), 500, null));
        }
    }
}
