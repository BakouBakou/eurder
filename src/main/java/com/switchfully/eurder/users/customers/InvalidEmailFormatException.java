package com.switchfully.eurder.users.customers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException() {
        super("The email format provided is invalid. Expected format: string@string.string");
    }
}
