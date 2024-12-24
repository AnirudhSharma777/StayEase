package com.example.demo.Dto;

import com.example.demo.Entities.Role;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Role role;

    public RegisterUserDto(String firstname,String lastname,String username,String email,String password,Role role){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role != null ? role : Role.CUSTOMER;
    }
}
