package com.example.demo.Exception;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException(String mgs){
        super(mgs);
    }

    public AccountAlreadyExistsException(){
        super();
    }
}
