package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import com.switchfully.eurder.items.dtos.ItemDto;
import com.switchfully.eurder.users.security.Feature;
import com.switchfully.eurder.users.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final SecurityService securityService;

    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody AddItemDto addItemDto, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.ADD_ITEM);
        return itemService.addItem(addItemDto);
    }

}
