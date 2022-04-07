package com.switchfully.eurder.items;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    public Item saveItem(Item item) {
        return item;
    }
}
