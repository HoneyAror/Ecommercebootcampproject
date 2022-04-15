package com.example.bootcamp.exceptions;

public class CannotUpdateException extends RuntimeException{
    public CannotUpdateException(String message)
    {
        super(message);
    }
}
