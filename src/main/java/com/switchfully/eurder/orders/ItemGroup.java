package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;

import java.time.LocalDate;
import java.util.Objects;

public class ItemGroup {

    public static final int DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK = 1;
    public static final int DAYS_BEFORE_SHIPPING_STOCK_INSUFFICIENT = 7;
    private final String id;
    private final int amount;
    private final double price;
    private final LocalDate shippingDate;
    private final Item item;

    public ItemGroup(int amount,  Item item) {
        this.amount = amount;
        this.item = item;
        this.id = this.item.getId();

        this.price = amount * this.item.getPrice();

        if (this.amount < this.item.getStock()){
            this.shippingDate = LocalDate.now().plusDays(DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK);
        } else {
            this.shippingDate = LocalDate.now().plusDays(DAYS_BEFORE_SHIPPING_STOCK_INSUFFICIENT);
        }
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroup itemGroup = (ItemGroup) o;
        return amount == itemGroup.amount && Objects.equals(id, itemGroup.id) && Objects.equals(shippingDate, itemGroup.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, shippingDate);
    }
}
