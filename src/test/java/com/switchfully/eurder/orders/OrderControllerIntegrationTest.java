package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemRepository;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.orders.dtos.OrderDto;
import com.switchfully.eurder.users.customers.Customer;
import com.switchfully.eurder.users.customers.CustomerRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderControllerIntegrationTest {

    private String customerId;
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        customerId = customerRepository.saveCustomer(new Customer(
                "test",
                "customer",
                "test@customer.com",
                "somewhere",
                "123"
        )).getId();

        item1 = itemRepository.saveItem(new Item("some Item", "it's kinda cool", 10.99, 10));
        item2 = itemRepository.saveItem(new Item("some Item2", "it's kinda cool2", 11.99, 11));
        item3 = itemRepository.saveItem(new Item("some Item3", "it's kinda cool3", 12.99, 12));
        item4 = itemRepository.saveItem(new Item("some Item4", "it's kinda cool4", 13.99, 13));
    }

    @Test
    void givenOrderItemsData_whenNewOrder_thenItemsAreOrdered() {
        //GIVEN
//        Item item1 = itemRepository.saveItem(new Item("some Item", "it's kinda cool", 10.99, 10));
//        Item item2 = itemRepository.saveItem(new Item("some Item2", "it's kinda cool2", 11.99, 11));
//        Item item3 = itemRepository.saveItem(new Item("some Item3", "it's kinda cool3", 12.99, 12));
//        Item item4 = itemRepository.saveItem(new Item("some Item4", "it's kinda cool4", 13.99, 13));
        ItemGroup itemGroup = new ItemGroup(5, item1);
        ItemGroup itemGroup2 = new ItemGroup(4, item2);
        ItemGroup itemGroup3 = new ItemGroup(3, item3);
        ItemGroup itemGroup4 = new ItemGroup(2, item4);
        Set<ItemGroup> itemGroupSet = new HashSet<>();
        itemGroupSet.add(itemGroup);
        itemGroupSet.add(itemGroup2);
        itemGroupSet.add(itemGroup3);
        itemGroupSet.add(itemGroup4);

        NewOrderDto expectedOrder = new NewOrderDto(itemGroupSet);

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
        Assertions.assertThat(actualOrder.getCustomerId()).isEqualTo(customerId);
        Assertions.assertThat(actualOrder.getItemGroupSet()).isEqualTo(expectedOrder.getItemGroupSet());
        Assertions.assertThat(actualOrder.totalPrice()).isEqualTo(expectedOrder.totalPrice());
    }
}