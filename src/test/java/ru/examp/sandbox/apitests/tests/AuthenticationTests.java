package ru.examp.sandbox.apitests.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import lombok.SneakyThrows;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.containsString;
import static ru.examp.sandbox.apitests.constants.Constants.*;


class AuthenticationTests {
    ObjectMapper objectMapper = new ObjectMapper();
    AuthCreds authCreds = new AuthCreds(USER, PASSWORD);


    @SneakyThrows
    @DisplayName("Проверка успешной аутентификации")
    @Test
     void authSuccessTest() {
        String authBody = objectMapper
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                        .writeValueAsString(authCreds);

        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authBody)
                .post(BASE_URL + AUTH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                ;
    }

    @SneakyThrows
    @DisplayName("Проверка НЕ успешной аутентификации")
    @Test
     void authUnSuccessTest() {
        String authBody = objectMapper
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .writeValueAsString(authCreds);

        RestAssured.given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(authBody)
                .when()
                .post(BASE_URL + AUTH_ENDPOINT)
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(containsString("\"Bad credentials\""));
    }
}

