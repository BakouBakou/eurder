package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class ItemGroupTest {

    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item("some Item", "it's kinda cool", 10.99, 10);
    }

    @Test
    void givenNewItemGroup_whenItemGroupAmountSmallerThanItemStock_thenItemGroupShippingDateIsNextDay() {
        //GIVEN
        ItemGroup itemGroup = new ItemGroup(item.getId(), 5);

        //WHEN

        //THEN
        Assertions.assertThat(itemGroup.getShippingDate()).isEqualTo(LocalDate.now().plusDays(1));
    }

    @Test
    void givenNewItemGroup_whenItemGroupAmountBiggerThanItemStock_thenItemGroupShippingDateIsNextDay() {
        //GIVEN
        ItemGroup itemGroup = new ItemGroup(item.getId(), 15);

        //WHEN

        //THEN
        Assertions.assertThat(itemGroup.getShippingDate()).isEqualTo(LocalDate.now().plusDays(7));
    }
}