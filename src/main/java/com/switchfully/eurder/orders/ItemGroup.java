package com.switchfully.eurder.orders;

import java.time.LocalDate;

public class ItemGroup {

    private final String id;
    private final int amount;
//    private final LocalDate shippingDate;

    public ItemGroup(String id, int amount) {
        this.id = id;
        this.amount = amount;
        //this.shippingDate = shippingDate;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }
}
