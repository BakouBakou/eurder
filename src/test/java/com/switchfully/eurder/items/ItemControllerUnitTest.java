package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class ItemControllerUnitTest {

    @LocalServerPort
    private int port;

    @Nested
    @DisplayName("Name format tests")
    class NameFormatTest {
        @Test
        void givenItemToAdd_whenAddItem_thenItemNameCannotBeNull() {
            //GIVEN
            AddItemDto newItem = new AddItemDto(null, "Video game that is very hard for your XboxSWitchtation", 59.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenItemToAdd_whenAddItem_thenItemNameCannotBeEmpty() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("", "Video game that is very hard for your XboxSWitchtation", 59.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenItemToAdd_whenAddItem_thenItemNameCannotBeBlank() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("   ", "Video game that is very hard for your XboxSWitchtation", 59.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    @DisplayName("Description format tests")
    class DescriptionFormatTest {
        @Test
        void givenItemToAdd_whenAddItem_thenItemDescriptionCannotBeNull() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("Elden Ring", null, 59.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenItemToAdd_whenAddItem_thenItemDescriptionCannotBeEmpty() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("Elden Ring", "", 59.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenItemToAdd_whenAddItem_thenItemDescriptionCannotBeBlank() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("Elden Ring", "    ", 59.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    @DisplayName("Price format tests")
    class PriceFormatTest {
        @Test
        void givenItemToAdd_whenAddItem_thenItemPriceCannotBe0() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("Elden Ring", "Video game that is very hard for your XboxSWitchtation", 0, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void givenItemToAdd_whenAddItem_thenItemPriceCannotBeNegative() {
            //GIVEN
            AddItemDto newItem = new AddItemDto("Elden Ring", "Video game that is very hard for your XboxSWitchtation", -10.99, 10);

            //WHEN
            //THEN
            RestAssured
                    .given()
                    .body(newItem)
                    .contentType(JSON)
                    .accept(JSON)
                    .when()
                    .port(port)
                    .post("/items")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Test
    void givenItemToAdd_whenAddItem_thenItemPriceCannotBeNegative() {
        //GIVEN
        AddItemDto newItem = new AddItemDto("Elden Ring", "Video game that is very hard for your XboxSWitchtation", 59.99, -10);

        //WHEN
        //THEN
        RestAssured
                .given()
                .body(newItem)
                .contentType(JSON)
                .accept(JSON)
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

}