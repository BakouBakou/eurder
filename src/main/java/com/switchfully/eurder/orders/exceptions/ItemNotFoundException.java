package com.switchfully.eurder.orders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String id) {
        super("Item with id " + id + " does not exist");
    }
}