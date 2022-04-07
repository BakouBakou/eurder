package com.switchfully.eurder.users.customers;

import com.switchfully.eurder.users.customers.dtos.CreateCustomerDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomersControllerUnitTest {

    @LocalServerPort
    private int port;

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenEmailCannotBeNull() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", null, "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                    .body(newCustomer)
                    .contentType(JSON)
                    .accept(JSON)
                .when()
                    .port(port)
                    .post("/customers")
                .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenEmailCannotBeEmpty() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenEmailCannotBeBlank() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "    ", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenFirstnameCannotBeNull() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto(null, "Buyer", "some@mail.com", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenFirstnameCannotBeEmpty() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("", "Buyer", "some@mail.com", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenFirstnameCannotBeBlank() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("   ", "Buyer", "some@mail.com", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenLastnameCannotBeNull() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", null, "some@mail.com", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenLastnameCannotBeEmpty() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "", "some@mail.com", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void givenCustomerToCreate_whenCreateCustomer_thenLastnameCannotBeBlank() {
        //GIVEN
        CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "    ", "some@mail.com", "10, money street. 1000 Materialist city", "123456");

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newCustomer)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}
