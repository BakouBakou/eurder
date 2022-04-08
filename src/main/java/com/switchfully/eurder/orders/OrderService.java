package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.orders.exceptions.EmptyOrderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    public OrderDto orderItems(String customerId, NewOrderDto newOrderDto) {

        checkInput(newOrderDto.getItemGroupSet().size() < 1, new EmptyOrderException());

        Order newOrder = orderMapper.toOrder(customerId, newOrderDto);
        Order savedOrder = orderRepository.saveOrder(newOrder);
        return orderMapper.toOrderDto(savedOrder);
    }

    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            logger.error(exceptionToThrow.getMessage());
            throw exceptionToThrow;
        }
    }
}
