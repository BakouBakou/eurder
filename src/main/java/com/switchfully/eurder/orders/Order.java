package com.switchfully.eurder.orders;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Order {
    private final String id;
    private final String customerId;
    private final Set<ItemGroup> itemGroupSet;

    public Order(String customerId, Set<ItemGroup> itemGroupSet) {
        this.id = UUID.randomUUID().toString();
        this.customerId = customerId;

        this.itemGroupSet = new HashSet<>();
        this.itemGroupSet.addAll(itemGroupSet);
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

    public double totalPrice() {
        return 0;
    }
}
