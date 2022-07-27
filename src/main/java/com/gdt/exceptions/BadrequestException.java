package com.gdt.exceptions;

public class BadrequestException extends RuntimeException{
    public BadrequestException(String message, String code) {
        super(message);
    }
}
