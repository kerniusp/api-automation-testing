package services;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UsersServices {

    public Response getAllUsers(){

        return given()
                .when()
                .get("/users")
                .then()
                .extract().response();
    }

    public Response getUsersById(int id){

        return given()
                .when()
                .get("/users/" + id)
                .then()
                .extract().response();
    }
}
