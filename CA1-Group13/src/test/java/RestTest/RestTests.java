/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestTest;

import entity.Address;
import entity.CityInfo;
import entity.Person;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import jsonmappers.PersonDetail;
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
        CityInfo ci = new CityInfo("0555", "Scanning");
        Address address = new Address("testgaden 1", "3.th", ci);
        Person p = new Person("Kurt", "Vonnegut", "kurt@vonnegut.org", address);
        p.addPhone("99999999", "Test phone");

        PersonDetail postedPerson = new PersonDetail(p);
        System.out.println("++++" + postedPerson);
        PersonDetail newPerson
                = given()
                        .contentType(ContentType.JSON)
                        .body(postedPerson)
                        .when().post("/api/person")
                        .as(PersonDetail.class);
        assertNotNull(newPerson.getFirstName());

        //GET
        PersonDetail gottenPerson = given()
                .contentType(ContentType.JSON)
                .when().get("/api/person/complete/99999999").as(PersonDetail.class);
        assertNotNull(gottenPerson.getFirstName());
        assertEquals("Kurt", gottenPerson.getFirstName());

        //DELETE
        PersonDetail deletedPerson = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/api/person/complete/99999999").as(PersonDetail.class);
        assertEquals("Kurt", deletedPerson.getFirstName());
    }
}
