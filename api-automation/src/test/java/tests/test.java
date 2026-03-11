package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import services.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test extends BaseTest {

    ProductService productService = new ProductService();




    @Test
    public void randomTest(){

        Response response = productService.getAllProducts();
        assertEquals(200,response.statusCode());
    }

}
