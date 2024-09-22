package ru.examp.sandbox.apitests.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
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



    @SneakyThrows
    @DisplayName("Проверка успешной аутентификации")
    @Test
     void authSuccessTest() {
        AuthCreds authCreds = new AuthCreds(USER, PASSWORD);
        String authBody = objectMapper
                        .writeValueAsString(authCreds);

        RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authBody)
                .post(BASE_URL + AUTH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .body()
                .asString()
                .contains("token")
                ;
    }

    @SneakyThrows
    @DisplayName("Проверка НЕ успешной аутентификации с неверным логином")
    @Test
     void authUnSuccessTestWrongLogin() {
        AuthCreds authCreds = new AuthCreds(BAD_USER, PASSWORD);
        String authBody = objectMapper
                .writeValueAsString(authCreds);

        RestAssured.given()
                .log().all()
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

    @SneakyThrows
    @DisplayName("Проверка НЕ успешной аутентификации с неверным паролем")
    @Test
    void authUnSuccessTestWrongPassword() {
        AuthCreds authCreds = new AuthCreds(USER, BAD_PASSWORD);
        String authBody = objectMapper
                .writeValueAsString(authCreds);

        RestAssured.given()
                .log().all()
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

