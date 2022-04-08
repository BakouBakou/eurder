package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.orders.ItemGroup;

import java.util.HashSet;
import java.util.Set;

public class OrderDto {
    private final String id;
    private final String customerId;
    private final Set<ItemGroup> itemGroupSet;
    private final double totalPrice;

    public OrderDto(String id, String customerId, Set<ItemGroup> itemGroupSet, double totalPrice) {
        this.id = id;
        this.customerId = customerId;

        this.itemGroupSet = new HashSet<>();
        this.itemGroupSet.addAll(itemGroupSet);

        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Set<ItemGroup> getItemGroupSet() {
        return itemGroupSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
