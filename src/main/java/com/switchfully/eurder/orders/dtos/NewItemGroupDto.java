package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.items.dtos.ItemToOrderDto;

public class NewItemGroupDto {

    private final int amount;
    private final ItemToOrderDto itemToOrderDto;

    public NewItemGroupDto(int amount, ItemToOrderDto itemToOrderDto) {
        this.amount = amount;
        this.itemToOrderDto = itemToOrderDto;
    }

    public int getAmount() {
        return amount;
    }

    public ItemToOrderDto getItemToOrderDto() {
        return itemToOrderDto;
    }
}
