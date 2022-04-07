package com.switchfully.eurder.users.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnknownUserException extends RuntimeException {
    public UnknownUserException() {
        super("Unknown user. Please log in to use this feature.");
    }
}
