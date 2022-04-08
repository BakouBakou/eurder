package com.switchfully.eurder.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyOrderException extends RuntimeException {
    public EmptyOrderException() {
        super("Your order is empty, please add at least 1 item.");
    }
}
