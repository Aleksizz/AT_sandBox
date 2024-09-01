package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static ru.examp.sandbox.apitests.constants.Constants.*;

 class GetBookingIdsTests {
    @DisplayName("Проверка успешного получения всех id бронирований")
    @Test
    void getBookingIdsSuccessTest() {
        Map<String, String> request = new HashMap<>();
        request.put("username", USER);
        request.put("password", PASSWORD);

        RestAssured.given()
                .log().ifValidationFails()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .get(BASE_URL + BOOKING_ENDPOINT)
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(containsString("\"bookingid\""));
    }

    @DisplayName("Проверка успешного получения  бронирований по Имени и Фамилии")
    @Test
    void getBookingIdsByNameSuccessTest() {
        Map<String, String> request = new HashMap<>();
        request.put("username", USER);
        request.put("password", PASSWORD);

        Map<String, String> pathParams = new HashMap<>();
        request.put("firstname", "Josh");
        request.put("lastname", "Allen");

        RestAssured.given()
                .log().ifValidationFails()
                .pathParams(pathParams)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .get(BASE_URL + BOOKING_ENDPOINT)
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body(containsString("\"bookingid\""));
    }

    @DisplayName("Проверка НЕ успешного получения  бронирований по Имени и Фамилии")
    @Test
    void getBookingIdsByNameUNSuccessTest() {
        Map<String, String> request = new HashMap<>();
        request.put("username", USER);
        request.put("password", PASSWORD);

        Map<String, String> pathParams = new HashMap<>();
        request.put("firstname", null);
        request.put("lastname", null);

        RestAssured.given()
                .log()
                .ifValidationFails()
                .pathParams(pathParams)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .get(BASE_URL + BOOKING_ENDPOINT)
                .then()
                .log().all()
                .assertThat()

                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }


}
