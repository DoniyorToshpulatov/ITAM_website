package com.example.exp;

public class RegionAlreadyExsistException extends RuntimeException{
    public RegionAlreadyExsistException(String message) {
        super(message);
    }
}
