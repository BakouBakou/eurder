package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.items.Item;

public class NewItemGroupDto {

    private final int amount;
    private final Item item;
    private double price;

    // all Dtos should use Dtos -> Item should  be a Dto as well
    // no cascade, but find the item in DB
    public NewItemGroupDto(int amount, Item item) {
        this.amount = amount;
        this.item = item;
        this.price = item.getPrice() * amount;
    }

    public int getAmount() {
        return amount;
    }

    public Item getItem() {
        return item;
    }

    public double getPrice() {
        return price;
    }
}
