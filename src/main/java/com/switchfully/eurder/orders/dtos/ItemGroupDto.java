package com.switchfully.eurder.orders.dtos;

import com.switchfully.eurder.items.dtos.ItemDto;

import java.time.LocalDate;

public class ItemGroupDto {
    private final String id;
    private final int amount;
    private final ItemDto itemDto;
    private final double price;
    private final LocalDate shippingDate;

    public ItemGroupDto(String id, int amount, ItemDto itemDto, double price, LocalDate shippingDate) {
        this.id = id;
        this.amount = amount;
        this.itemDto = itemDto;
        this.price = price;
        this.shippingDate = shippingDate;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public ItemDto getItemDto() {
        return itemDto;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
