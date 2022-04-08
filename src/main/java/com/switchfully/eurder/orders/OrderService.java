package com.switchfully.eurder.orders;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public OrderDto orderItems(String customerId, NewOrderDto newOrderDto) {

        Order newOrder = orderMapper.toOrder(customerId, newOrderDto);
        Order savedOrder = orderRepository.saveOrder(newOrder);
        return orderMapper.toOrderDto(savedOrder);
    }
}
