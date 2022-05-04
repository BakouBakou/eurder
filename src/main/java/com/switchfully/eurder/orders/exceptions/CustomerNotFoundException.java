package com.switchfully.eurder.orders.exceptions;

import com.switchfully.eurder.users.customers.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerId) {
        super("Customer with id " + customerId + " does not exist");
    }
}
