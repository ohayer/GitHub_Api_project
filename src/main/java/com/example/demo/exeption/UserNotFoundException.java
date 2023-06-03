package com.example.demo.exeption;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Cannot find user");

    }


}
