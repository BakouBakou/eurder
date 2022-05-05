package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemRepository;
import com.switchfully.eurder.items.dtos.ItemToOrderDto;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.users.customers.Customer;
import com.switchfully.eurder.users.customers.CustomerRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class OrderControllerIntegrationTest {

    private String customerId;
    private Item item1;
    private Item item2;

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        customerId = customerRepository.save(new Customer(
                "test",
                "customer",
                "test1@customer.com",
                "somewhere",
                "123"
        )).getId();

        item1 = itemRepository.save(new Item("some Item", "it's kinda cool", 10.99, 10));
        item2 = itemRepository.save(new Item("some Item2", "it's kinda cool2", 11.99, 11));
    }

    @Test
    void givenOrderItemsData_whenNewOrder_thenItemsAreOrdered() {
        //GIVEN

        Set<NewItemGroupDto> newItemGroupDtoSet = new HashSet<>();
        int item1Amount = 5;
        newItemGroupDtoSet.add(new NewItemGroupDto(item1Amount, new ItemToOrderDto(item1.getId())));
        int item2Amount = 4;
        newItemGroupDtoSet.add(new NewItemGroupDto(item2Amount, new ItemToOrderDto(item2.getId())));

        NewOrderDto expectedOrder = new NewOrderDto(newItemGroupDtoSet);
        double expectedTotalPrice = item1.getPrice() * item1Amount
                + item2.getPrice() * item2Amount;

        //WHEN
        OrderDto actualOrder = RestAssured
                .given()
                .body(expectedOrder)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers/" + customerId + "/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(OrderDto.class);

        //THEN
        Assertions.assertThat(actualOrder.getId()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(actualOrder.getCustomer().getId()).isEqualTo(customerId);
        Assertions.assertThat(actualOrder.getItemGroupDtoSet().stream()
                        .map(itemGroupDto -> itemGroupDto.getItemDto().getId())
                        .collect(Collectors.toSet()))
                .containsAll(expectedOrder.getNewItemGroupDtoSet().stream()
                        .map(newItemGroupDto -> newItemGroupDto.getItemToOrderDto().getId())
                        .collect(Collectors.toSet()));
        Assertions.assertThat(actualOrder.getTotalPrice()).isEqualTo(expectedTotalPrice);
    }
}