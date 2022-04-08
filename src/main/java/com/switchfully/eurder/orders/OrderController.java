package com.switchfully.eurder.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/customers/{customerId}/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemsDto orderItems(@PathVariable String customerId, @RequestBody NewOrderItemsDto newOrderItemsDto) {
        return orderService.orderItems(customerId, newOrderItemsDto);
    }
}
