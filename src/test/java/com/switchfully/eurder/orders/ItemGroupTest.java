package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ItemGroupTest {

    @Test
    void givenNewItemGroup_whenItemGroupAmountSmallerThanItemStock_thenItemGroupShippingDateIsNextDay() {
        //GIVEN
        Item item = new Item("some Item", "it's kinda cool", 10.99, 10);
        ItemGroup itemGroup = new ItemGroup(item.getId(), 5);

        //WHEN

        //THEN
        Assertions.assertThat(itemGroup.getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }
}