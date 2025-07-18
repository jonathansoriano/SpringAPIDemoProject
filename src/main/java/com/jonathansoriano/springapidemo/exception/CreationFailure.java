package com.jonathansoriano.springapidemo.exception;

public class CreationFailure extends RuntimeException{
    public CreationFailure(String message) {
        super(message);
    }
}
