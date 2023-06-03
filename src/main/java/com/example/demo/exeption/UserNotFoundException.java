package com.example.demo.exeption;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Cannot find user");

    }


}
