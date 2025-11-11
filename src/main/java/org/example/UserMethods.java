package org.example;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UserMethods {

    public static String baseURL = "https://stellarburgers.education-services.ru";
    public static String userLoginPath = "/api/auth/login";
    public static String userPath = "/api/auth/user";
    public static String userRegisterPath = "api/auth/register";
    public static final int IMPLICIT_WAIT = 7;


    @Step("Создаю пользователя")
    public static Response createNewUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(userRegisterPath);
    }

    @Step("Логин пользователя")
    public static Response loginUser(UserLogin userLogin) {
        return given()
                .header("Content-type", "application/json")
                .body(userLogin)
                .when()
                .post(userLoginPath);
    }

    @Step("Удаление пользователя")
    public static ValidatableResponse deleteUser(String accessToken) {
        return given().log().all()
                .header("Authorization", accessToken)
                .baseUri("https://stellarburgers.education-services.ru")
                .when().delete("/api/auth/user")
                .then().
                log().all().statusCode(202);
    }
}