package com.switchfully.eurder.items;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody AddItemDto addItemDto) {
        return new ItemDto("someId", addItemDto.getName(), addItemDto.getDescription(), addItemDto.getPrice(), addItemDto.getStock());
    }

}
