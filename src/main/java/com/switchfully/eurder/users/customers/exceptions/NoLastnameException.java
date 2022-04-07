package com.switchfully.eurder.users.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoLastnameException extends RuntimeException {
    public NoLastnameException() {
        super("No lastname has been provided");
    }
}
