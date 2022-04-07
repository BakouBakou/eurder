package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.items.exceptions.NegativeStockException;
import com.switchfully.eurder.items.exceptions.NoDescriptionException;
import com.switchfully.eurder.items.exceptions.NoNameException;
import com.switchfully.eurder.items.exceptions.NullOrNegativePriceException;
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
        checkInput(isNotProvided(addItemDto.getDescription()), new NoDescriptionException());
        checkInput(addItemDto.getPrice() <= 0, new NullOrNegativePriceException());
        checkInput(addItemDto.getStock() < 0, new NegativeStockException());

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
