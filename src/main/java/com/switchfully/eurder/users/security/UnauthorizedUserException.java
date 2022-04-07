package com.switchfully.eurder.users.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedUserException extends RuntimeException {
    public UnauthorizedUserException() {
        super("You don't have enough rights to use this feature.");
    }
}
