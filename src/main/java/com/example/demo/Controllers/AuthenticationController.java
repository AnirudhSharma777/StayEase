package com.example.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthenticationRequest;
import com.example.demo.Dto.EmailRequest;
import com.example.demo.Dto.RegisterUserDto;
import com.example.demo.Dto.VerifyUserDto;
import com.example.demo.Entities.Role;
import com.example.demo.Entities.User;

import com.example.demo.Services.AuthenticationService;
import com.example.demo.ResponseDto.AuthenticationResponse;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto dto) {
        if (dto.getRole() == null) {
            dto.setRole(Role.CUSTOMER); // Default to CUSTOMER
        }
        User userEntity = authenticationService.signup(dto);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody VerifyUserDto verifyUserDto) {
        try {

            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationToken(@RequestBody EmailRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email cannot be null or empty");
        }

        try {
            authenticationService.resendVerficationCode(request.getEmail());
            return ResponseEntity.ok("Verification code resent successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        
    }
}