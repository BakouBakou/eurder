package com.switchfully.eurder.orders;

import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.users.customers.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/{customerId}/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto orderItems(@PathVariable String customerId, @RequestBody NewOrderDto newOrderDto) {
        return orderService.orderItems(customerId, newOrderDto);
    }
}
