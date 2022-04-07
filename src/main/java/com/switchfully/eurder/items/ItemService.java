package com.switchfully.eurder.items;

import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto addItem(AddItemDto addItemDto) {

        Item newItem = itemMapper.toItem(addItemDto);
        Item savedItem = itemRepository.saveItem(newItem);
        return itemMapper.toItemDto(savedItem);
    }
}
