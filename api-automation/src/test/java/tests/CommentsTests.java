package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.example.models.Comment;
import org.junit.jupiter.api.Test;
import services.CommentsServices;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotNull;

public class CommentsTests extends BaseTest {


    CommentsServices commentsTests = new CommentsServices();


    @Test
    public void testGetAllComments(){

        Response response = commentsTests.getAllComments();
        List<Comment> comments = response.jsonPath().getList("", Comment.class);

        assertEquals(200,response.statusCode());
        assertNotNull(comments.get(0).getId());
        assertNotNull(comments.get(0).getBody());
    }

    @Test
    public void testGetCommentById(){

        Response response = commentsTests.getCommentById(1);
        Comment comment = response.as(Comment.class);

        assertEquals(200, response.statusCode());
        assertEquals(1, comment.getId());
        assertNotNull(comment.getEmail());
    }

    @Test
    public void testAddComment(){

        Response response = commentsTests.addComment(new Comment(1,"Name","Email","Body"));
        Comment comment = response.as(Comment.class);

        assertEquals(200,response.statusCode());
        assertEquals("Name",comment.getName());
        assertEquals("Email",comment.getEmail());
    }

}
