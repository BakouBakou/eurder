package com.switchfully.eurder.users.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException() {
        super("The email provided is already in use. Only one customer account is allowed per email address.");
    }
}
