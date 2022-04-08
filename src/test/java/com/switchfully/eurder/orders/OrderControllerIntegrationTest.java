package com.switchfully.eurder.orders;

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

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;
    private String customerId;

    @BeforeEach
    void setUp() {
        customerId = customerRepository.saveCustomer(new Customer(
                "test",
                "customer",
                "test@customer.com",
                "somewhere",
                "123"
        )).getId();
    }

    @Test
    void givenOrderItemsData_whenNewOrder_thenItemsAreOrdered() {
        //GIVEN
        Set<ItemGroup> itemGroupSet = new HashSet<>();
        itemGroupSet.add(new ItemGroup("id1", 5));
        itemGroupSet.add(new ItemGroup("id2", 4));
        itemGroupSet.add(new ItemGroup("id3", 3));
        itemGroupSet.add(new ItemGroup("id4", 2));

        NewOrderDto expectedOrder = new NewOrderDto(
                itemGroupSet
        );

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