package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.items.dtos.ItemToOrderDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemMapper {

    public Item toItem(AddItemDto addItemDto) {
        return new Item(
                addItemDto.getName(),
                addItemDto.getDescription(),
                addItemDto.getPrice(),
                addItemDto.getStock()
        );
    }


    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getStock()
        );
    }
}
