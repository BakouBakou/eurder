package com.switchfully.eurder.orders;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/customers/{customerId}/order")
public class OrderController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItemsDto orderItems(@PathVariable String customerId, @RequestBody NewOrderItemsDto newOrderItemsDto) {
        Set<ItemGroup> itemGroupSet = new HashSet<>();
        itemGroupSet.add(new ItemGroup("id1", 5));
        itemGroupSet.add(new ItemGroup("id2", 4));
        itemGroupSet.add(new ItemGroup("id3", 3));
        itemGroupSet.add(new ItemGroup("id4", 2));
        return new OrderItemsDto("something", customerId, itemGroupSet);
    }
}
