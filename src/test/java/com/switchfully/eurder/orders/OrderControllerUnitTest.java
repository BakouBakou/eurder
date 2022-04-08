package com.switchfully.eurder.orders;

import com.switchfully.eurder.users.customers.Customer;
import com.switchfully.eurder.users.customers.CustomerRepository;
import io.restassured.RestAssured;
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
class OrderControllerUnitTest {

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
    void givenEmptyOrderData_whenOrderItems_thenBadRequestIsThrown() {
        //GIVEN
        Set<ItemGroup> itemGroupSet = new HashSet<>();
        NewOrderDto emptyOrder = new NewOrderDto(
                itemGroupSet
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
}