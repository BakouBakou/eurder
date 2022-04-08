package com.switchfully.eurder.orders.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.switchfully.eurder.orders.ItemGroup;

import java.util.HashSet;
import java.util.Set;

public class NewOrderDto {

    private Set<ItemGroup> itemGroupSet;

    public NewOrderDto() {
    }

    @JsonCreator
    public NewOrderDto(@JsonProperty("itemGroupSet") Set<ItemGroup> itemGroupSet) {

        this.itemGroupSet = new HashSet<>();
        this.itemGroupSet.addAll(itemGroupSet);

    }

    public Set<ItemGroup> getItemGroupSet() {
        return itemGroupSet;
    }

    public double totalPrice() {
        return 0;
    }

}
