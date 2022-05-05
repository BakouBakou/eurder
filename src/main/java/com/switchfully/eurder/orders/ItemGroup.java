package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ITEM_GROUP")
public class ItemGroup {

    public static final int DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK = 1;
    public static final int DAYS_BEFORE_SHIPPING_STOCK_INSUFFICIENT = 7;

    @Id
    private String id;

    @Column(name = "AMOUNT")
    private int amount;
    @ManyToOne
    @JoinColumn(name = "FK_ITEM_ID")
    private Item item;

    @Column(name = "PRICE")
    private double price;
    @Column(name = "SHIPPING_DATE")
    private LocalDate shippingDate;

    @ManyToOne
    @JoinColumn(name = "FK_ORDER_DETAIL_ID")
    private Order order;

    public ItemGroup() {
    }

    public ItemGroup(int amount, Item item) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.item = item;

        this.price = item.getPrice() * amount;

        // simplify
        this.shippingDate = calculateShippingDate();
    }

    private LocalDate calculateShippingDate() {
        if (this.amount < this.item.getStock()){
            return LocalDate.now().plusDays(DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_BEFORE_SHIPPING_STOCK_INSUFFICIENT);
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

    public Order getOrder() {
        return order;
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
