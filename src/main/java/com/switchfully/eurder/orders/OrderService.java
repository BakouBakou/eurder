package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemRepository;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.orders.exceptions.EmptyOrderException;
import com.switchfully.eurder.orders.exceptions.InvalidItemAmountException;
import com.switchfully.eurder.users.customers.Customer;
import com.switchfully.eurder.users.customers.CustomerRepository;
import com.switchfully.eurder.users.customers.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);
    private final ItemRepository itemRepository;
    private final CustomerService customerService;

    public OrderService(OrderMapper orderMapper, OrderRepository orderRepository, ItemRepository itemRepository, CustomerService customerService) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.customerService = customerService;
    }

    public OrderDto orderItems(String customerId, NewOrderDto newOrderDto) {

        logger.info("Initiating new order for customer " + customerId);

        Customer customer = customerService.findCustomerById(customerId);

        checkInput(newOrderDto.getNewItemGroupDtoSet().size() < 1, new EmptyOrderException());

        newOrderDto.getNewItemGroupDtoSet().stream()
                .map(itemGroup -> itemGroup.getAmount())
                .forEach(amount -> checkInput(amount <= 0, new InvalidItemAmountException()));

        Order newOrder = orderMapper.toOrder(customer, newOrderDto);
        Order savedOrder = orderRepository.save(newOrder);

        updateItemStock(savedOrder);

        logger.info("new order with ID " + savedOrder.getId() + " saved for customer " + customerId);
        return orderMapper.toOrderDto(savedOrder);
    }

    private void updateItemStock(Order order) {
        order.getItemGroupSet().forEach(itemGroup -> {
            itemGroup.getItem().setStock(itemGroup.getItem().getStock() - itemGroup.getAmount());
            itemRepository.save(itemGroup.getItem());
        });
    }


    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            logger.error(exceptionToThrow.getMessage());
            throw exceptionToThrow;
        }
    }


}
