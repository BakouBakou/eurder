package com.switchfully.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NegativeStockException extends RuntimeException {
    public NegativeStockException() {
        super("An item cannot have an amount in the stock lower to 0");
    }
}
