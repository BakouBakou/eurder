package com.switchfully.eurder.orders.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchfully.eurder.orders.ItemGroup;

import java.util.HashSet;
import java.util.Set;

public class NewOrderDto {

    private Set<ItemGroup> itemGroupSet;
    private double totalPrice;

    public NewOrderDto() {
    }

    @JsonCreator
    public NewOrderDto(@JsonProperty("itemGroupSet") Set<ItemGroup> itemGroupSet) {

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
