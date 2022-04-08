package com.switchfully.eurder.orders;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {
    public OrderItemsDto orderItems(String customerId, NewOrderItemsDto newOrderItemsDto) {
        Set<ItemGroup> itemGroupSet = new HashSet<>();
        itemGroupSet.add(new ItemGroup("id1", 5));
        itemGroupSet.add(new ItemGroup("id2", 4));
        itemGroupSet.add(new ItemGroup("id3", 3));
        itemGroupSet.add(new ItemGroup("id4", 2));
        return new OrderItemsDto("something", customerId, itemGroupSet);
    }
}
