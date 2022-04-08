package com.switchfully.eurder.orders;

import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public Order toOrder(String customerId, NewOrderDto newOrderDto) {
        return new Order(customerId, newOrderDto.getItemGroupSet());
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getCustomerId(), order.getItemGroupSet());
    }
}
