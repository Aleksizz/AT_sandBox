package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ru.examp.sandbox.apitests.constants.Constants.*;

 class PutUpdateBookingtests {

    @DisplayName("Проверка успешного обновления бронирования")
    @Test
    void updateBookingSuccessTest() {

        BaseAuth bt = new BaseAuth();
        bt.baseAuthAndGetToken();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .log().ifValidationFails()
                .accept(ContentType.JSON)
                .cookie("token", bt.token)
                .body("{"
                        + "\"firstname\": \"Olololo\","
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
                .put(BASE_URL + BOOKING_ENDPOINT+BOOKING_ID)
                .then()
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON);
    }
}
