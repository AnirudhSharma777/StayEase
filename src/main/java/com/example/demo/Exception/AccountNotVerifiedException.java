package com.example.demo.Exception;

public class AccountNotVerifiedException extends RuntimeException {

    public AccountNotVerifiedException(String mgs){
        super(mgs);
    }

    public AccountNotVerifiedException(){
        super();
    }
}
