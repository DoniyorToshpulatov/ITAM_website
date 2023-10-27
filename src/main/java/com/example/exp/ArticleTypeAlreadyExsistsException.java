package com.example.exp;

public class ArticleTypeAlreadyExsistsException extends RuntimeException{
    public ArticleTypeAlreadyExsistsException(String message) {
        super(message);
    }
}
