package com.switchfully.eurder.orders;

import com.switchfully.eurder.items.Item;
import com.switchfully.eurder.items.ItemRepository;
import com.switchfully.eurder.items.dtos.ItemToOrderDto;
import com.switchfully.eurder.orders.dtos.NewItemGroupDto;
import com.switchfully.eurder.orders.dtos.NewOrderDto;
import com.switchfully.eurder.users.customers.Customer;
import com.switchfully.eurder.users.customers.CustomerRepository;
import io.restassured.RestAssured;
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

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class OrderControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    private String customerId;
    private Item item;

    @BeforeEach
    void setUp() {
        customerId = customerRepository.save(new Customer(
                "test",
                "customer",
                "test@customer.com",
                "somewhere",
                "123"
        )).getId();
        item = itemRepository.save(new Item(
                "test",
                "test",
                10.05,
                3
        ));
    }

    @Test
    void givenEmptyOrderData_whenOrderItems_thenBadRequestIsThrown() {
        //GIVEN
        Set<NewItemGroupDto> newItemGroupDtoSet = new HashSet<>();
        NewOrderDto emptyOrder = new NewOrderDto(
                newItemGroupDtoSet
        );
        //WHEN
        //THEN
        RestAssured
                .given()
                .body(emptyOrder)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers/" + customerId + "/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenItemThatDoesNotExist_whenOrderItems_thenBadRequestIsThrown() {
        //GIVEN
        Set<NewItemGroupDto> newItemGroupDtoSet = new HashSet<>();
        newItemGroupDtoSet.add(new NewItemGroupDto(5, new ItemToOrderDto("IDoNotExist")));
        NewOrderDto emptyOrder = new NewOrderDto(
                newItemGroupDtoSet
        );
        //WHEN
        //THEN
        RestAssured
                .given()
                .body(emptyOrder)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers/" + customerId + "/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenNullOrNegativeAmountOfItems_whenOrderItems_thenBadRequestIsThrown() {
        //GIVEN
        Set<NewItemGroupDto> newItemGroupDtoSet = new HashSet<>();
        newItemGroupDtoSet.add(new NewItemGroupDto(-5, new ItemToOrderDto(item.getId())));
        NewOrderDto emptyOrder = new NewOrderDto(
                newItemGroupDtoSet
        );
        //WHEN
        //THEN
        RestAssured
                .given()
                .body(emptyOrder)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers/" + customerId + "/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerThatDoesNotExist_whenOrderItems_thenBadRequestIsThrown() {
        //GIVEN
        Set<NewItemGroupDto> newItemGroupDtoSet = new HashSet<>();
        newItemGroupDtoSet.add(new NewItemGroupDto(2, new ItemToOrderDto(item.getId())));
        NewOrderDto emptyOrder = new NewOrderDto(
                newItemGroupDtoSet
        );
        //WHEN
        //THEN
        RestAssured
                .given()
                .body(emptyOrder)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/customers/" + "customeridthatdoesnotexist" + "/order")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}