package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.users.customers.Customer;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {
    private final String id;
    private final Customer customer;
    private final Set<ItemGroupDto> itemGroupDtoSet;
    private final double totalPrice;

    public OrderDto(String id, Customer customer, Set<ItemGroupDto> itemGroupSet, double totalPrice) {
        this.id = id;
        this.customer = customer;

        this.itemGroupDtoSet = new HashSet<>();
        this.itemGroupDtoSet.addAll(itemGroupSet);

        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ItemGroupDto> getItemGroupDtoSet() {
        return itemGroupDtoSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
