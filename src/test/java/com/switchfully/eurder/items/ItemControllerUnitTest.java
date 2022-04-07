package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

}