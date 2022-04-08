package com.switchfully.eurder.orders;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OrderRepository {

    private final ConcurrentHashMap<String,Order> ordersDatabase = new ConcurrentHashMap<>();

    public Order saveOrder(Order order) {
        ordersDatabase.put(order.getId(), order);
        return ordersDatabase.get(order.getId());
    }
}
