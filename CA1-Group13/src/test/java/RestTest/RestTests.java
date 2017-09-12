/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestTest;

import entity.Person;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author bloch
 */
public class RestTests {
    
public RestTests()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8084;
        RestAssured.basePath = "/CA1-Group13";
        RestAssured.defaultParser = Parser.JSON;
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testGetPersons()
    {
        System.out.println("getPersons");
    }
    
    @Test
    public void serverIsRunning()
    {
        System.out.println("serverIsRunning");
        
        given().
        when().get().
        then().statusCode(200);
    }
    
    @Test
    public void postGetDeletePerson()
    {
        System.out.println("postGetDeletePerson");
        
        //POST
        Person postedPerson = new Person("Kurt", "Wonnegut", "mo@gmail.com");
        Person newPerson =
        given()
        .contentType(ContentType.JSON)
        .body(postedPerson)
        .when().post("/api/person")
        .as(Person.class);
        assertNotNull(newPerson.getId());
    
        //GET
        Person gottenPerson = given()
        .contentType(ContentType.JSON)
        .when().get("/api/person/complete/" + newPerson.getId()).as(Person.class); 
        assertNotNull(gottenPerson.getId());
        assertEquals("Kurt", gottenPerson.getFirstName());

        //DELETE
        Person deletedPerson = given()
        .contentType(ContentType.JSON)
        .when().delete("/api/person/" + newPerson.getId()).as(Person.class);
        assertEquals("Kurt", deletedPerson.getFirstName());
    }
}
