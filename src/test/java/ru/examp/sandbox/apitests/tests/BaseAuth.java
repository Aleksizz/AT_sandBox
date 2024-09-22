package ru.examp.sandbox.apitests.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.apache.http.HttpStatus;

import static ru.examp.sandbox.apitests.constants.Constants.*;
import static ru.examp.sandbox.apitests.constants.Constants.AUTH_ENDPOINT;

class BaseAuth {
    ObjectMapper objectMapper = new ObjectMapper();

    String token;


    String  baseAuthAndGetToken() throws JsonProcessingException {
        AuthCreds authCreds = new AuthCreds(USER, PASSWORD);

        String authBody = objectMapper
                .writeValueAsString(authCreds);

        ResponseBody body = RestAssured.given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(authBody)
                .post(BASE_URL + AUTH_ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response()
                .body()
                ;

        token = body.asString().substring(9, body.asString().length() - 1);

        return token;
     }
}
