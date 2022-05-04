package com.switchfully.eurder.orders;

import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.users.customers.Customer;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {


    private final ItemGroupMapper itemGroupMapper;

    public OrderMapper(ItemGroupMapper itemGroupMapper) {
        this.itemGroupMapper = itemGroupMapper;
    }

    public Order toOrder(Customer customer, NewOrderDto newOrderDto) {
        return new Order(customer, itemGroupMapper.toItemGroupSet(newOrderDto.getNewItemGroupDtoSet()), newOrderDto.getTotalPrice());
    }

    public OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCustomer(),
                itemGroupMapper.toItemGroupDtoSet(order.getItemGroupSet()),
                order.getTotalPrice());
    }
}
