package com.kardoaward.mobileapp.events.exeption;

public class ConflictError extends RuntimeException {
    public ConflictError(String message) {
        super(message);
    }
}
