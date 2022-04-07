package com.switchfully.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullOrNegativePriceException extends RuntimeException {
    public NullOrNegativePriceException() {
        super("An item cannot have a price lower or equal to 0");
    }
}
