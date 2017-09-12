/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityFacades;

import entity.Person;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PersonFacadeTest {

    private EntityManagerFactory emf;
    private PersonFacade pf;
    private String PU = "PU_DERBY";

    public PersonFacadeTest() {
        pf = new PersonFacade();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU);
        pf.addEntityManagerFactory(emf);
        pf.addPerson(new Person("bob", "hansen", "Bob@Hop.dk"));
        pf.addPerson(new Person("Per", "Nielsen", "Per@dollar.dk"));
        pf.addPerson(new Person("Henrik", "Henningsen", "op@top.dk"));

    }

    @After
    public void tearDown() {
        System.out.println("TearDown");
        emf.close();
        HashMap<String, Object> puproperties = new HashMap();
        puproperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        Persistence.generateSchema(PU, puproperties);
        Persistence.generateSchema(PU, new HashMap());
    }

    @Test
    public void testGetPerson_int() {
        System.out.println("getPerson");
        Person expResult = new Person("bob", "hansen", "Bob@Hop.dk");

        Person result = pf.getPerson(1);

        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

//    /**
//     * Test of getPerson method, of class PersonFacade.
//     */
//    @Test
//    public void testGetPerson_long() {
//        System.out.println("getPerson");
//        long phoneNumber = 0L;
//        PersonFacade instance = null;
//        Person expResult = null;
//        Person result = instance.getPerson(phoneNumber);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of editPerson method, of class PersonFacade.
     */
    @Test
    public void testEditPerson() {
        Person p = pf.getPerson(1);
        p.setFirstName("frans");
        Person editedPerson = pf.editPerson(p);
        Person person = new Person("frans", "hansen", "Bob@Hop.dk");
        assertEquals(editedPerson.getFirstName(), person.getFirstName());

    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
    @Test
    public void testDeletePerson_int() {
        System.out.println("deletePerson");
        int id = 0;
        PersonFacade instance = null;
        Person expResult = null;
        Person result = instance.deletePerson(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPerson method, of class PersonFacade.
     */
    @Test
    public void testAddPerson() {
        System.out.println("addPerson");

        Person expResult = new Person("Ole", "Larsen", "Bob@Hop.dk");;
        Person result = pf.addPerson(new Person("Ole", "Larsen", "Bob@Hop.dk"));
        assertEquals(4, pf.getPersons().size());

    }

    /**
     * Test of getPersons method, of class PersonFacade.
     */
    @Test
    public void testGetPersons_0args() {
        System.out.println("getPersons");
        PersonFacade instance = null;
        List<Person> expResult = null;
        List<Person> result = instance.getPersons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersons method, of class PersonFacade.
     */
    @Test
    public void testGetPersons_String() {
        System.out.println("getPersons");
        String zipCode = "";
        PersonFacade instance = null;
        List<Person> expResult = null;
        List<Person> result = instance.getPersons(zipCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
    @Test
    public void testDeletePerson_Person() {
        System.out.println("deletePerson");
        pf.deletePerson(1);
        assertEquals(2, pf.getPersons().size());
        ;
    }

}
