package com.project;

public class CustomException extends Exception {
    CustomException(String message, Throwable exception) {
        super(message, exception);
    }
}