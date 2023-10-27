package com.example.exp;

public class TagAlreadyExsistException extends RuntimeException {
    public TagAlreadyExsistException(String message) {
        super(message);
    }
}
