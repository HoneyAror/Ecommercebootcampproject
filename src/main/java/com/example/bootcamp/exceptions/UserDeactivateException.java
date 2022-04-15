package com.example.bootcamp.exceptions;

public class UserDeactivateException extends RuntimeException{
    public UserDeactivateException(String messasge)
    {
        super(messasge);
    }
}