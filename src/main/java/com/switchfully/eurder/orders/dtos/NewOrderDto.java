package com.switchfully.eurder.orders.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashSet;
import java.util.Set;

public class NewOrderDto {

    private Set<NewItemGroupDto> newItemGroupDtoSet;
    private double totalPrice;

    public NewOrderDto() {
    }

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // if I wanted to have only 1 constructor with only 1 parameter and make sure this one is used
    public NewOrderDto(Set<NewItemGroupDto> newItemGroupDtoSet) {

        this.newItemGroupDtoSet = new HashSet<>();
        this.newItemGroupDtoSet.addAll(newItemGroupDtoSet);
        // total price should be calculated on Order itself based on info from the db, easier to find where calculation is made
        this.totalPrice = calculateTotalPrice(newItemGroupDtoSet);

    }

    public Set<NewItemGroupDto> getNewItemGroupDtoSet() {
        return newItemGroupDtoSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private double calculateTotalPrice(Set<NewItemGroupDto> newItemGroupDtoSet) {
        return newItemGroupDtoSet.stream()
                .mapToDouble(newItemGroupDto -> newItemGroupDto.getPrice())
                .sum();
    }

}
