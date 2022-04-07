package com.switchfully.eurder.users.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("No user found for this user/password combination");
    }
}
