package com.switchfully.eurder.orders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderItemsDto {
    private final String id;
    private final String customerId;
    private final Set<ItemGroup> itemGroupSet;

    public OrderItemsDto(String id, String customerId, Set<ItemGroup> itemGroupSet) {
        this.id = id;
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
