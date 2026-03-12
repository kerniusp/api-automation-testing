package services;

import io.restassured.response.Response;
import org.example.models.Comment;

import static io.restassured.RestAssured.given;

public class CommentsServices {

    public Response getAllComments(){

        return given()
                .when()
                .get("/comments")
                .then()
                .extract().response();
    }

    public Response getCommentById(int id){

        return given()
                .when()
                .get("/comments/" + id)
                .then()
                .extract().response();
    }

    public Response addComment(Comment comment){

        return given()
                .body(comment)
                .when()
                .post("/comments")
                .then()
                .extract().response();
    }

    public Response updateComment(int id, Comment comment){

        return given()
                .body(comment)
                .when()
                .patch("/comments/" + id)
                .then()
                .extract().response();
    }

    public Response deleteComment(int id){

        return given()
                .when()
                .delete("/comments/" + id)
                .then()
                .extract().response();
    }
}
