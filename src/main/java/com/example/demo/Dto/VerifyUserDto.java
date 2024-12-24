package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyUserDto {
    private String email;
    private String verificationCode;

   
}
