package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;

import java.time.LocalDate;

public class ItemGroup {

    public static final int DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK = 1;
    public static final int DAYS_BEFORE_SHIPPING_STOCK_INSUFFICIENT = 7;
    private final String id;
    private final int amount;
    private final LocalDate shippingDate;

    public ItemGroup(int amount, Item item) {
        this.id = item.getId();
        this.amount = amount;

        if (this.amount < item.getStock()){
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


    public LocalDate getShippingDate() {
        return shippingDate;
    }
}
