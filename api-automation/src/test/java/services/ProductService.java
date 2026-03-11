package services;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ProductService {


    
    public Response getAllProducts(){

        return given()
                .when()
                .get("/productsList")
                .then()
                .extract().response();
    }

    public Response searchProductByPost(String productName){

        return given()
                .formParam("search_product", productName)
                .when()
                .post("/searchProduct")
                .then()
                .extract().response();
    }

    public Response searchProductByGet(String productName){

        return given()
                .queryParam("search_product", productName)
                .when()
                .get("/searchProduct")
                .then()
                .extract().response();
    }
}
