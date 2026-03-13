package tests;

import base.BaseTest;
import io.restassured.response.Response;
import org.example.models.user.Person;
import org.junit.jupiter.api.Test;
import services.UsersServices;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTests extends BaseTest {

    UsersServices usersServices = new UsersServices();

    @Test
    public void testGetAllUsers(){

        Response response = usersServices.getAllUsers();
        List<Person> persons = response.jsonPath().getList("",Person.class);

        assertEquals(200,response.statusCode());
        assertEquals(10,persons.size());
    }

    @Test
    public void testResponseTime(){

        Response response = usersServices.getAllUsers();

        assertTrue("Response took longer than 2 seconds",response.getTime() < 2000);

    }

    @Test
    public void testUsersCompanyInformation(){

        Response response = usersServices.getUsersById(1);
        Person person = response.as(Person.class);

        assertEquals(200,response.statusCode());
        assertEquals("Romaguera-Crona",person.getCompany().getName());
        assertEquals("Multi-layered client-server neural-net",person.getCompany().getCatchPhrase());


    }
}
