package com.kardoaward.mobileapp.exeption;

public class ConflictError extends RuntimeException {
    public ConflictError(String message) {
        super(message);
    }
}
