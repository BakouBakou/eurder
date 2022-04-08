package com.switchfully.eurder.orders;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public OrderDto orderItems(String customerId, NewOrderDto newOrderDto) {

        Order newOrder = orderMapper.toOrder(newOrderDto);
        return orderMapper.toOrderDto(newOrder);

//        Set<ItemGroup> itemGroupSet = new HashSet<>();
//        itemGroupSet.add(new ItemGroup("id1", 5));
//        itemGroupSet.add(new ItemGroup("id2", 4));
//        itemGroupSet.add(new ItemGroup("id3", 3));
//        itemGroupSet.add(new ItemGroup("id4", 2));
//        return new OrderDto("something", customerId, itemGroupSet);
    }
}
