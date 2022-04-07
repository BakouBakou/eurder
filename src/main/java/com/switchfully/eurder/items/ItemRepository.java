package com.switchfully.eurder.items;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ItemRepository {

    private final ConcurrentHashMap<String, Item> itemsDatabase = new ConcurrentHashMap<>();

    public Item saveItem(Item item) {
        itemsDatabase.put(item.getId(),item);
        return itemsDatabase.get(item.getId());
    }
}
