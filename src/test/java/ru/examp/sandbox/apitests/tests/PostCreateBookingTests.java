package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.examp.sandbox.apitests.constants.Constants.*;


 class PostCreateBookingTests {
    @DisplayName("Проверка успешного создания бронирования")
    @Test
    void createBookingSuccessTest() {

        RestAssured.given()
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .body("{"
                                + "\"firstname\": \"Jimmy\","
                     +  "\"lastname\" : \"Browny\","
                + "\"totalprice\" : \"111\","
               + "\"depositpaid\" : \"true\","
                +"\"bookingdates\" : " + "{" +
                                "\"checkin\" : \"2018-01-01\","
                   + "\"checkout\" : \"2019-01-01\""
                        + "},"
       + "\"additionalneeds\" : \"Breakfast\""
    +"}")
                .when()
                .post(BASE_URL + BOOKING_ENDPOINT)
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }
}
