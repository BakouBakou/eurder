package com.switchfully.eurder.items;

import com.switchfully.eurder.items.dtos.AddItemDto;
import com.switchfully.eurder.items.dtos.ItemDto;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ItemControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void givenItemData_whenAddItem_thenItemIsAddedToTheSystem() {
        //GIVEN
        AddItemDto expectedItem = new AddItemDto("Elden Ring", "Video game that is very hard for your XboxSWitchtation", 59.99, 10);

        //WHEN
        ItemDto actualItem = RestAssured
                .given()
                .body(expectedItem)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/items")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(ItemDto.class);

        //THEN
        Assertions.assertThat(actualItem.getId()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(actualItem.getName()).isEqualTo(expectedItem.getName());
        Assertions.assertThat(actualItem.getDescription()).isEqualTo(expectedItem.getDescription());
        Assertions.assertThat(actualItem.getPrice()).isEqualTo(expectedItem.getPrice());
        Assertions.assertThat(actualItem.getStock()).isEqualTo(expectedItem.getStock());
    }
}