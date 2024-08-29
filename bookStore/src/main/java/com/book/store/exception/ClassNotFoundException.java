package com.book.store.exception;

public class ClassNotFoundException extends RuntimeException {
    public ClassNotFoundException(String message) {
        super(message);
    }
}