package com.example.bootcamp.exceptions;

public class AccountLockedException extends RuntimeException{
    public AccountLockedException(String message)
    {
        super(message);
    }
}
