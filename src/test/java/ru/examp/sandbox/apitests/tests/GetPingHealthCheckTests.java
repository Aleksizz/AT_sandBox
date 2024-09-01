package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


 class GetPingHealthCheckTests {

    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String PING_ENDPOINT = "/ping";

    @DisplayName("Проверка доступности сервиса")
    @Test
    void authSuccessTest() {

        RestAssured.given()
                .when()
                .get(BASE_URL + PING_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }
}
