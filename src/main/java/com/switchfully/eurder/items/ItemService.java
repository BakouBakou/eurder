package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import com.switchfully.eurder.items.dtos.ItemDto;
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

        checkInput(isNotProvided(addItemDto.getName()), new NoNameException());

        Item newItem = itemMapper.toItem(addItemDto);
        Item savedItem = itemRepository.saveItem(newItem);
        return itemMapper.toItemDto(savedItem);
    }


    private boolean isNotProvided(String userInput) {
        return userInput == null || userInput.isEmpty() || userInput.isBlank();
    }

    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            throw exceptionToThrow;
        }
    }
}
