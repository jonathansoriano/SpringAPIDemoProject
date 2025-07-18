package com.jonathansoriano.springapidemo.exception;

public class SearchNotFound extends RuntimeException{
    public SearchNotFound(String message) {
        super(message);
    }
}
