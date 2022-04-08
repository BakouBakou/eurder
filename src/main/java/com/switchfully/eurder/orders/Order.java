package com.switchfully.eurder.orders;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Order {
    private final String id;
    private final String customerId;
    private final Set<ItemGroup> itemGroupSet;
    private final double totalPrice;

    public Order(String customerId, Set<ItemGroup> itemGroupSet, double totalPrice) {
        this.id = UUID.randomUUID().toString();
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
