package services;

import io.restassured.response.Response;
import org.example.models.Post;

import static io.restassured.RestAssured.given;

public class PostsServices {

    public Response getAllPosts(){

        return given()
                .when()
                .get("/posts")
                .then()
                .extract().response();
    }

    public Response getPostById(int id){

        return given()
                .when()
                .get("/posts/" + id)
                .then()
                .extract().response();
    }

    public Response getPostsByUserId(int id){

        return given()
                .queryParam("userId", id)
                .when()
                .get("/posts" )
                .then()
                .extract().response();
    }

    public Response getAllCommentsFromPost(int id){

        return given()
                .when()
                .get("/posts/" + id + "/comments")
                .then()
                .extract().response();
    }

    public Response addPost(Post post){

        return given()
                .body(post)
                .when()
                .post("/posts")
                .then()
                .extract().response();
    }

    public Response updatePost(int id, Post post){

        return given()
                .body(post)
                .when()
                .put("/posts/" + id)
                .then()
                .extract().response();
    }

    public Response partiallyUpdatePost(int id, Post post){

        return given()
                .body(post)
                .when()
                .patch("/posts/" + id)
                .then()
                .extract().response();
    }

    public Response deletePostById(int id){

        return given()
                .when()
                .delete("/posts/" + id)
                .then()
                .extract().response();
    }



}
