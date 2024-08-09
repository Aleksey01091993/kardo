package com.kardoaward.mobileapp.events.exeption;

public class NullRequestException extends RuntimeException {
    public NullRequestException(String message) {
        super(message);
    }
}
