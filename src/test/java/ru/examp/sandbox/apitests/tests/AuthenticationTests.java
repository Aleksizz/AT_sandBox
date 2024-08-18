package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.containsString;

 class AuthenticationTests {
    private static final String USER = "admin";
    private static final String PASSWORD = "password123";
    private static final String BASE_URL = "https://restful-booker.herokuapp.com";
    private static final String AUTH_ENDPOINT = "/auth";

    @DisplayName("Проверка успешной аутентификации")
    @Test
     void authSuccessTest() {
        Map<String, String> request = new HashMap<>();
        request.put("username", USER);
        request.put("password", PASSWORD);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(containsString("\"token\""));
    }

    @DisplayName("Проверка НЕ успешной аутентификации")
    @Test
     void authUnSuccessTest() {
        Map<String, String> request = new HashMap<>();
        request.put("username", null);
        request.put("password", PASSWORD);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(containsString("\"Bad credentials\""));
    }
}
