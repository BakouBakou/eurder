package com.switchfully.eurder.users.customers;

import com.switchfully.eurder.users.customers.dtos.CreateCustomerDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;


    @Nested
    @DisplayName("Email format tests")
    class EmailFormatTest {
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
        void givenCustomerToCreate_whenEmailNoAtNoDot_thenBadRequestIsThrown() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "somemailcom", "10, money street. 1000 Materialist city", "123456");

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
        void givenCustomerToCreate_whenEmailNoAt_thenBadRequestIsThrown() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "somemail.com", "10, money street. 1000 Materialist city", "123456");

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
        void givenCustomerToCreate_whenEmailNoDot_thenBadRequestIsThrown() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "some@mailcom", "10, money street. 1000 Materialist city", "123456");

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
        void givenCustomerToCreate_whenEmailDotBeforeAt_thenBadRequestIsThrown() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "some.mail@com", "10, money street. 1000 Materialist city", "123456");

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
        void givenCustomerToCreate_whenEmailSpecialCharacter_thenBadRequestIsThrown() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "some@mail.c_om", "10, money street. 1000 Materialist city", "123456");

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
        void givenCustomerToCreate_whenEmailAlreadyExists_thenBadRequestIsThrown() {
            //GIVEN
            String emailThatExists = "some@mail.com";
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", emailThatExists, "10, money street. 1000 Materialist city", "123456");
            //WHEN
            customerRepository.saveCustomer(new Customer(
                    "Not",
                    "Inspired",
                    emailThatExists,
                    "somewhere",
                    "987654"
            ));

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

    @Nested
    @DisplayName("Firstname format tests")
    class FirstnameFormatTest {
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
    }

    @Nested
    @DisplayName("Lastname format tests")
    class LastnameFormatTest {

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


    @Nested
    @DisplayName("Address format tests")
    class AddressFormatTest {

        @Test
        void givenCustomerToCreate_whenCreateCustomer_thenAddressCannotBeNull() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "some@mail.com", null, "123456");

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
        void givenCustomerToCreate_whenCreateCustomer_thenAddressCannotBeEmpty() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "some@mail.com", "", "123456");

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
        void givenCustomerToCreate_whenCreateCustomer_thenAddressCannotBeBlank() {
            //GIVEN
            CreateCustomerDto newCustomer = new CreateCustomerDto("Compulsive", "Buyer", "some@mail.com", "    ", "123456");

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


}
