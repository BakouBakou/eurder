package com.switchfully.eurder.orders.dtos;

import java.util.HashSet;
import java.util.Set;

public class NewOrderDto {

    private Set<NewItemGroupDto> newItemGroupDtoSet;

    public NewOrderDto() {
    }

//    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES) // if I wanted to have only 1 constructor with only 1 parameter and make sure this one is used
    public NewOrderDto(Set<NewItemGroupDto> newItemGroupDtoSet) {

        this.newItemGroupDtoSet = new HashSet<>();
        this.newItemGroupDtoSet.addAll(newItemGroupDtoSet);
    }

    public Set<NewItemGroupDto> getNewItemGroupDtoSet() {
        return newItemGroupDtoSet;
    }

}
