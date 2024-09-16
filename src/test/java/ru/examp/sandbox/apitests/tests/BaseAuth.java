package ru.examp.sandbox.apitests.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static ru.examp.sandbox.apitests.constants.Constants.*;
import static ru.examp.sandbox.apitests.constants.Constants.AUTH_ENDPOINT;

class BaseAuth {
    String token = "";

    public BaseAuth() {
    }

    String  baseAuthAndGetToken(){


         Map<String, String> request = new HashMap<>();
         request.put("username", USER);
         request.put("password", PASSWORD);

         token = RestAssured.given()
                 .contentType(ContentType.JSON)
                 .body(request)
                 .when()
                 .post(BASE_URL + AUTH_ENDPOINT)
                 .then()
                 .log().all()
                 .statusCode(HttpStatus.SC_OK)
                 .contentType(ContentType.JSON)
                 .extract()
                 .body()
                 .asString()
                 .substring(9, 26)

         ;

         return token;
     }
}
