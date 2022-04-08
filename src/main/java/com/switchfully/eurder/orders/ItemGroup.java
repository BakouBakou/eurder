package com.switchfully.eurder.orders;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemGroup itemGroup = (ItemGroup) o;
        return amount == itemGroup.amount && Objects.equals(id, itemGroup.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
