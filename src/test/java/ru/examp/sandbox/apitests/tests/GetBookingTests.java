package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static ru.examp.sandbox.apitests.constants.Constants.*;
import static ru.examp.sandbox.apitests.constants.Constants.BOOKING_ENDPOINT;

 class GetBookingTests {
    @DisplayName("Проверка успешного получения бронирования по id")
    @Test
    void getBookingDetailsByIdSuccessTest() {
        Map<String, String> request = new HashMap<>();
        request.put("username", USER);
        request.put("password", PASSWORD);

        RestAssured.given()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .get(BASE_URL + BOOKING_ENDPOINT+ BOOKING_ID)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Susan"))
                .body("lastname", equalTo("Ericsson"))
                .body("totalprice", equalTo(249))
                .body("depositpaid", equalTo(false))
                .body("checkin", equalTo("2021-01-31"))
                .body("checkout", equalTo("2021-11-08"));

    }
}
