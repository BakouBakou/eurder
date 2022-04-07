package com.switchfully.eurder.items;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoNameException extends RuntimeException {
    public NoNameException() {
        super("No name has been provided");
    }
}
