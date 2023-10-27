package com.example.exp;

public class CategoryAlreadyExsistException extends RuntimeException {
    public CategoryAlreadyExsistException(String message) {
        super(message);
    }
}
