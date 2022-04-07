package com.switchfully.eurder.users.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoAddressException extends RuntimeException {
    public NoAddressException() {
        super("No address has been provided");
    }
}
