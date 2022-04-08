package com.switchfully.eurder.items;

import java.util.UUID;

public class Item {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Item() {
    }

    public Item(String name, String description, double price, int stock) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
