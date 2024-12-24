package com.example.demo.Services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AuthenticationRequest;
import com.example.demo.Dto.RegisterUserDto;
import com.example.demo.Dto.VerifyUserDto;
import com.example.demo.Entities.Token;
import com.example.demo.Entities.TokenType;
import com.example.demo.Entities.User;
import com.example.demo.Exception.AccountAlreadyExistsException;
import com.example.demo.Exception.AccountNotVerifiedException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Repositories.TokenRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.ResponseDto.AuthenticationResponse;
import com.example.demo.Utils.JwtService;

import jakarta.mail.MessagingException;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            EmailService emailService, JwtService jwtServie, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.jwtService = jwtServie;
        this.tokenRepository = tokenRepository;
    }

    public User signup(RegisterUserDto input) {

        boolean isEmailExist = userRepository.findByEmail(input.getEmail()).isPresent();
        if (isEmailExist) {
            throw new AccountAlreadyExistsException("Account already exists using this email");
        }
        var user = User.builder()
                .firstname(input.getFirstname())
                .lastname(input.getLastname())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(input.getRole())
                .verificationCode(generateVerificationCode())
                .verificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15))
                .enabled(false)
                .build();

        sendVerificationEmail(user);
        var savedUser = userRepository.save(user);
        return savedUser;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not Found"));

        if (!user.isEnabled()) {
            throw new AccountNotVerifiedException("Account not verified. Please verify your account.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var jwtToken = jwtService.generateToken(user);
        var expired = jwtService.getExpirationTime();

        saveUserToken(user, jwtToken);
        
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .expiresIn(expired)
                .build();
    }

    public void verifyUser(VerifyUserDto input) {
        Optional<User> optionalUser = userRepository.findByEmail(input.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
                throw new RuntimeException("Verification code has expired");
            }

            
            if (user.getVerificationCode().equals(input.getVerificationCode())) {
                user.setVerificationCode(null);
                user.setVerificationCodeExpiresAt(null);
                user.setEnabled(true);
                userRepository.save(user);
            } else {
                throw new RuntimeException("Invalid verification code");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public void resendVerficationCode(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.isEnabled()) {
                throw new RuntimeException("Account is already verified");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiresAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private void sendVerificationEmail(User user) {
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    // private void revokeAllUserTokens(User user) {
    // var validUserTokens = tokenRepository.findAllValidTokenByUser()
    // if (validUserTokens)
    // return;
    // validUserTokens.forEach(token -> {
    // token.setExpired(true);
    // token.setRevoked(true);
    // });
    // tokenRepository.saveAll(validUserTokens);
    // }
}
