package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.orders.ItemGroup;

import java.util.HashSet;
import java.util.Set;

public class NewOrderDto {

    private Set<ItemGroup> itemGroupSet;
    private double totalPrice;

    public NewOrderDto() {
    }

    public NewOrderDto(Set<ItemGroup> itemGroupSet) {

        this.itemGroupSet = new HashSet<>();
        this.itemGroupSet.addAll(itemGroupSet);

        this.totalPrice = calculateTotalPrice();

    }

    public Set<ItemGroup> getItemGroupSet() {
        return itemGroupSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice() {
        return itemGroupSet.stream()
                .mapToDouble(itemGroup -> itemGroup.getPrice())
                .sum();
    }

}
