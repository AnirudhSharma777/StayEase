package com.example.demo.Exception;

public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException(String message) {
        super(message);
    }

    public HotelNotFoundException() {
        super();
    }
}
