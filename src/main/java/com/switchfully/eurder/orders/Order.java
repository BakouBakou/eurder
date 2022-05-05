package com.switchfully.eurder.orders;

import com.switchfully.eurder.users.customers.Customer;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "ORDER_DETAIL")
public class Order {

    @Id
    private String id;
    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private Customer customer;
    // Make unidirectional but that mean-s that fetching an order fetches all the itemGroups -> could impact performance -> getting the itemGroup and checking for the order might be more performant
    // fetch lazy is default, Hibernate does the onetomany only when it is actually needed
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
//    @JoinColumn(name = "FK_ORDER_DETAIL_ID")
    private Set<ItemGroup> itemGroupSet;
    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    public Order(Customer customer, Set<ItemGroup> itemGroupSet) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;

        this.itemGroupSet = new HashSet<>();
        this.itemGroupSet.addAll(itemGroupSet);

        this.totalPrice = itemGroupSet.stream()
                .mapToDouble(itemGroup -> itemGroup.getPrice())
                .sum();
    }

    public Order() {

    }

    public String getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Set<ItemGroup> getItemGroupSet() {
        return itemGroupSet;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
