package com.switchfully.eurder.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/{customerId}/order")
public class OrderController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public OrderItemsDto orderItems(@PathVariable String customerId, @RequestBody NewOrderItemsDto newOrderItemsDto) {
        return null;
    }
}
