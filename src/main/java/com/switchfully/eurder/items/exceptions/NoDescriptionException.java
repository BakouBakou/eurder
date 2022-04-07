package com.switchfully.eurder.items.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoDescriptionException extends RuntimeException {
    public NoDescriptionException() {
        super("No description has been provided");
    }
}
