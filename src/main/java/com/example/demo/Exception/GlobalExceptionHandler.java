package com.example.demo.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.ResponseDto.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotVerifiedException.class)
    public ResponseEntity<Object> handleAccountNotVerifiedException(AccountNotVerifiedException ex) {
        // You can create a custom response structure here
        return new ResponseEntity<>(new ErrorResponse("ACCOUNT_NOT_VERIFIED", ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<Object> handlerUserNotFoundException(AccountAlreadyExistsException ex) {

        return new ResponseEntity<>(new ErrorResponse("ACCOUNT_ALREADY_EXISTS", ex.getMessage()),
                HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse("USER_NOT_FOUND", ex.getMessage()), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<Object> handleHotelNotFoundException(HotelNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse("HOTEL_NOT_FOUND", ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    
}
