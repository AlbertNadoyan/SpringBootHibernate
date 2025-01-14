package com.example.springboothibernate.exception;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException(String message) {
        super(message);
    }
}
