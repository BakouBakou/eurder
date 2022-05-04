package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemRepository;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.orders.exceptions.CustomerNotFoundException;
import com.switchfully.eurder.orders.exceptions.EmptyOrderException;
import com.switchfully.eurder.orders.exceptions.InvalidItemAmountException;
import com.switchfully.eurder.orders.exceptions.ItemNotFoundException;
import com.switchfully.eurder.users.customers.Customer;
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
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerRepository customerRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerRepository = customerRepository;
    }

    public OrderDto orderItems(String customerId, NewOrderDto newOrderDto) {

        logger.info("Initiating new order for customer " + customerId);

        checkInput(newOrderDto.getNewItemGroupDtoSet().size() < 1, new EmptyOrderException());

        Optional<Customer> customerById = customerRepository.findById(customerId);
        checkInput(customerById.isEmpty(), new CustomerNotFoundException(customerId));
        Customer customer = customerById.get();

//        newOrderDto.getNewItemGroupDtoSet().stream()
//                .map(itemGroup -> itemGroup.getId())
//                .forEach(id -> checkInput(itemRepository.findItemById(id).isEmpty(), new ItemNotFoundException(id)));
//        newOrderDto.getNewItemGroupDtoSet().stream()
//                .map(itemGroup -> itemGroup.getAmount())
//                .forEach(amount -> checkInput(amount <= 0, new InvalidItemAmountException()));

        Order newOrder = orderMapper.toOrder(customer, newOrderDto);
        Order savedOrder = orderRepository.saveOrder(newOrder);
        logger.info("new order with ID " + savedOrder.getId() + " saved for customer " + customerId);
        return orderMapper.toOrderDto(savedOrder);
    }

    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            logger.error(exceptionToThrow.getMessage());
            throw exceptionToThrow;
        }
    }
}
