package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.ItemMapper;
import com.switchfully.eurder.items.ItemService;
import com.switchfully.eurder.orders.dtos.ItemGroupDto;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ItemGroupMapper {

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemGroupMapper(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    public ItemGroup toItemGroup(NewItemGroupDto newItemGroupDto) {
        return new ItemGroup(
                newItemGroupDto.getAmount(),
                itemService.findItem(newItemGroupDto.getItemToOrderDto())
        );
    }

    public Set<ItemGroup> toItemGroupSet(Set<NewItemGroupDto> newItemGroupDtoSet) {
        return newItemGroupDtoSet.stream()
                .map(newItemGroupDto -> toItemGroup(newItemGroupDto))
                .collect(Collectors.toSet());
    }

    public ItemGroupDto toItemGroupDto (ItemGroup itemGroup) {
        return new com.switchfully.eurder.orders.dtos.ItemGroupDto(
                itemGroup.getId(),
                itemGroup.getAmount(),
                itemMapper.toItemDto(itemGroup.getItem()),
                itemGroup.getPrice(),
                itemGroup.getShippingDate()
        );
    }

    public Set<ItemGroupDto> toItemGroupDtoSet (Set<ItemGroup> itemGroupSet){
        return itemGroupSet.stream()
                .map(itemGroup -> toItemGroupDto(itemGroup))
                .collect(Collectors.toSet());
    }
}
