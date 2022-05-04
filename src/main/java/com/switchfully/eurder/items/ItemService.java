package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.items.exceptions.NegativeStockException;
import com.switchfully.eurder.items.exceptions.NoDescriptionException;
import com.switchfully.eurder.items.exceptions.NoNameException;
import com.switchfully.eurder.items.exceptions.NullOrNegativePriceException;
import com.switchfully.eurder.users.customers.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final Logger logger = LoggerFactory.getLogger(ItemService.class);

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto addItem(AddItemDto addItemDto) {
        logger.info("addItem started");

        // checks duplication
        checkInput(isNotProvided(addItemDto.getName()), new NoNameException());
        checkInput(isNotProvided(addItemDto.getDescription()), new NoDescriptionException());
        checkInput(addItemDto.getPrice() <= 0, new NullOrNegativePriceException());
        checkInput(addItemDto.getStock() < 0, new NegativeStockException());

        Item newItem = itemMapper.toItem(addItemDto);
        Item savedItem = itemRepository.saveItem(newItem);
        logger.info("item created in the database with id: " + savedItem.getId());
        return itemMapper.toItemDto(savedItem);
    }

    private boolean isNotProvided(String userInput) {
        return userInput == null || userInput.isEmpty() || userInput.isBlank();
    }

    private void checkInput(Boolean isInvalidInput, RuntimeException exceptionToThrow) {
        if (isInvalidInput) {
            logger.error(exceptionToThrow.getMessage());
            throw exceptionToThrow;
        }
    }
}
