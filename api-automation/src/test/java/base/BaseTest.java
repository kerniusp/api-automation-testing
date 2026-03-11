package base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeAll
    public static void setup(){

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        RestAssured.requestSpecification =
                given()
                        .header("Content-type", "application/json")
                        .header("Accept", "application/json");
    }
}
