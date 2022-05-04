package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.items.Item;

import java.time.LocalDate;

public class ItemGroupDto {
    private final String id;
    private final int amount;
    private final Item item;
    private final double price;
    private final LocalDate shippingDate;

    public ItemGroupDto(String id, int amount, Item item, double price, LocalDate shippingDate) {
        this.id = id;
        this.amount = amount;
        this.item = item;
        this.price = price;
        this.shippingDate = shippingDate;
    }

    public String getId() {
        return id;
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

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
