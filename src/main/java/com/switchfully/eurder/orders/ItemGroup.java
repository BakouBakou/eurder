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
    @OneToOne
    @JoinColumn(name = "FK_ITEM_ID")
    private Item item;

    @Column(name = "PRICE")
    private double price;
    @Column(name = "SHIPPING_DATE")
    private LocalDate shippingDate;

    @Column(name = "FK_ORDER_DETAIL_ID")
    private String orderId;

    public ItemGroup(int amount, Item item) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.item = item;

        this.price = item.getPrice() * amount;

        this.shippingDate = LocalDate.now().plusDays(calculateShippingDate());
    }

    public ItemGroup() {

    }

    private int calculateShippingDate() {
        if (this.amount < this.item.getStock()){
            return DAYS_BEFORE_SHIPPING_ITEM_IN_STOCK;
        }
        return DAYS_BEFORE_SHIPPING_STOCK_INSUFFICIENT;
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

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
