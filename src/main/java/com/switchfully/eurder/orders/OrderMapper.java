package com.switchfully.eurder.orders;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(NewOrderDto newOrderDto) {
        return new Order(newOrderDto.getCustomerId(), newOrderDto.getItemGroupSet());
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getCustomerId(), order.getItemGroupSet());
    }
}
