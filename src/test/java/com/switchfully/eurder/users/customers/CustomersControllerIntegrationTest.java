package com.switchfully.eurder.users.customers;

import com.switchfully.eurder.users.customers.dtos.CreateCustomerDto;
import com.switchfully.eurder.users.customers.dtos.CustomerDto;
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
class CustomersControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Test
    void givenCustomerData_whenRegisterCustomer_thenCustomerIsAddedToTheSystem() {
        //GIVEN
        CreateCustomerDto expectedCustomer = new CreateCustomerDto("Baker", "El Muhur", "coolmail@domain.com", "10, cool street. 5000 Namur", "0474/12.34.56");

        //WHEN
        CustomerDto actualCustomer = RestAssured
                .given()
                    .body(expectedCustomer)
                    .accept(JSON)
                    .contentType(JSON)
                .when()
                    .port(port)
                    .post("/customers")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(CustomerDto.class);

        //THEN
        Assertions.assertThat(actualCustomer.getId()).isNotNull().isNotBlank().isNotEmpty();
        Assertions.assertThat(actualCustomer.getFirstname()).isEqualTo(expectedCustomer.getFirstname());
        Assertions.assertThat(actualCustomer.getLastname()).isEqualTo(expectedCustomer.getLastname());
        Assertions.assertThat(actualCustomer.getEmail()).isEqualTo(expectedCustomer.getEmail());
        Assertions.assertThat(actualCustomer.getAddress()).isEqualTo(expectedCustomer.getAddress());
        Assertions.assertThat(actualCustomer.getPhoneNumber()).isEqualTo(expectedCustomer.getPhoneNumber());
    }
}