package com.example.demo.Services;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.Entities.User;
import com.example.demo.Repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRespository;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRespository = userRepository;
    }

    public List<User> allUser() {
        return userRespository.findAll()
                .stream()
                .collect(Collectors.toList());
    }

}
