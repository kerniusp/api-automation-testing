package tests;

import base.BaseTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.example.models.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import services.PostsServices;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.jupiter.api.Assertions.*;

public class PostTests extends BaseTest {

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("init.sql");

    @BeforeAll
    public static void setUpDatabase(){
        postgres.start();

        System.setProperty("DB_URL",postgres.getJdbcUrl());
        System.setProperty("DB_USER",postgres.getUsername());
        System.setProperty("DB_PASS",postgres.getPassword());
    }

    PostsServices postsServices = new PostsServices();


    @Test
    public void validatePostSchema(){
        Response response = postsServices.getAllPosts();
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/post-schema.json"));
    }

    @Test
    public void testGetAllPosts(){

        Response response = postsServices.getAllPosts();
        List<Post> posts = response.jsonPath().getList("", Post.class);

        assertEquals(200,response.statusCode());
        assertFalse(posts.isEmpty());

        posts.forEach(object -> {
            System.out.println(object.getId() + " Title:" + object.getTitle() + " Body:" + object.getBody() + " UserId:" + object.getUserId());
        });

    }

    @Test
    public void testGetPostById(){

        Response response = postsServices.getPostById(1);
        Post post = response.as(Post.class);

        assertEquals(200,response.statusCode());
        assertEquals(1,post.getId());
        assertFalse(post.getTitle().isEmpty());
        assertFalse(post.getBody().isEmpty());

    }

    @Test
    public void testPostFilter(){

        Response response = postsServices.getPostsByUserId(1);
        List<Post> posts = response.jsonPath().getList("",Post.class);

        assertEquals(200, response.statusCode());
        assertFalse(posts.isEmpty());

        posts.forEach(post -> {
            assertEquals(1, post.getUserId());
        });
    }

    @Test
    public void testAddingPost(){

        Response response = postsServices.addPost(new Post("New_Post_Title","New_Post_Body",2));
        Post post = response.as(Post.class);

        assertEquals(201,response.statusCode());


        assertEquals("New_Post_Title",post.getTitle());
        assertEquals("New_Post_Body",post.getBody());
        assertEquals(2,post.getUserId());
    }

    @Test
    public void testAddingPostWithMissingField(){

        Response response = postsServices.addPost(new Post("New_Post_Body",2));
        Post post = response.as(Post.class);

        assertEquals(400,response.statusCode());
    }

    @Test
    public void testUpdatingPost(){
        Response response = postsServices.updatePost(1,new Post("New_Post_Title","New_Post_Body",2));
        Post post = response.as(Post.class);

        assertEquals(200,response.statusCode());
        assertEquals("New_Post_Title",post.getTitle());
        assertEquals("New_Post_Body",post.getBody());
        assertEquals(2,post.getUserId());
    }

    @Test
    public void testDeletePost(){

        Response response = postsServices.deletePostById(1);

        assertEquals(200,response.statusCode());

    }
}
