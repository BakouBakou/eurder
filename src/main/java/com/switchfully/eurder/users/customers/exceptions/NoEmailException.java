package com.switchfully.eurder.users.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoEmailException extends RuntimeException {
    public NoEmailException() {
        super("No email has been provided");
    }
}
