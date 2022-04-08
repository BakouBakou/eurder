package com.switchfully.eurder.orders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidItemAmountException extends RuntimeException {
    public InvalidItemAmountException() {
        super("The amount of each ordered item cannot be equal to or lower than 0");
    }
}