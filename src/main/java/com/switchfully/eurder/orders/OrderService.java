package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemRepository;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.orders.exceptions.EmptyOrderException;
import com.switchfully.eurder.users.customers.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public OrderDto orderItems(String customerId, NewOrderDto newOrderDto) {

        checkInput(newOrderDto.getItemGroupSet().size() < 1, new EmptyOrderException());

        checkInput(elementExistsInDatabase(customerRepository.findCustomerById(customerId)), new CustomerNotFoundException());

        newOrderDto.getItemGroupSet().stream()
                .map(itemGroup -> itemGroup.getId())
                .forEach(id -> checkInput(elementExistsInDatabase(itemRepository.findItemById(id)), new ItemNotFoundException()));

        Order newOrder = orderMapper.toOrder(customerId, newOrderDto);
        Order savedOrder = orderRepository.saveOrder(newOrder);
        return orderMapper.toOrderDto(savedOrder);
    }

    private <T> boolean elementExistsInDatabase(Optional<T> element) {
        return element.isPresent();
    }

    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            logger.error(exceptionToThrow.getMessage());
            throw exceptionToThrow;
        }
    }
}
