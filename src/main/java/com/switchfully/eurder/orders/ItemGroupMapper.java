package com.switchfully.eurder.orders;

import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ItemGroupMapper {

    public ItemGroup toItemGroup(NewItemGroupDto newItemGroupDto) {
        return new ItemGroup(newItemGroupDto.getAmount(), newItemGroupDto.getItem(), newItemGroupDto.getPrice());
    }

    public Set<ItemGroup> toItemGroupSet(Set<NewItemGroupDto> newItemGroupDtoSet) {
        return newItemGroupDtoSet.stream()
                .map(newItemGroupDto -> toItemGroup(newItemGroupDto))
                .collect(Collectors.toSet());
    }

    public com.switchfully.eurder.orders.dtos.ItemGroupDto toItemGroupDto (ItemGroup itemGroup) {
        return new com.switchfully.eurder.orders.dtos.ItemGroupDto(
                itemGroup.getId(),
                itemGroup.getAmount(),
                itemGroup.getItem(),
                itemGroup.getPrice(),
                itemGroup.getShippingDate()
        );
    }

    public Set<com.switchfully.eurder.orders.dtos.ItemGroupDto> toItemGroupDtoSet (Set<ItemGroup> itemGroupSet){
        return itemGroupSet.stream()
                .map(itemGroup -> toItemGroupDto(itemGroup))
                .collect(Collectors.toSet());
    }
}
