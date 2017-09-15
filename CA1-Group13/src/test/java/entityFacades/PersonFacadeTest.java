/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityFacades;

import entity.Address;
import entity.CityInfo;
import entity.Person;
import entity.Phone;
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

    public PersonFacadeTest()
    {
        pf = new PersonFacade();
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
        System.out.println("SetUp");
        emf = Persistence.createEntityManagerFactory(PU);
        pf.addEntityManagerFactory(emf);
        pf.addPerson(new Person("bob", "hansen", "Bob@Hop.dk"));
        pf.addPerson(new Person("Per", "Nielsen", "Per@dollar.dk"));
        pf.addPerson(new Person("Henrik", "Henningsen", "op@top.dk"));
//        CityInfo ci = new CityInfo("HardZip", "9999");
//        Address address = new Address("EnSted", "2. tv", ci);
//        ci.addAddress(address);
//        Person p = pf.getPerson(1);
//        p.setAddress(address);
//        pf.editPerson(p);

    }

    @After
    public void tearDown()
    {
        System.out.println("TearDown");
        emf.close();
        HashMap<String, Object> puproperties = new HashMap();
        puproperties.put("javax.persistence.sql-load-script-source", "scripts/ClearDB.sql");
        Persistence.generateSchema(PU, puproperties);
        Persistence.generateSchema(PU, new HashMap());
    }

    @Test
    public void testGetPerson_int()
    {
        System.out.println("getPerson");
        Person expResult = new Person("bob", "hansen", "Bob@Hop.dk");

        Person result = pf.getPerson(1);

        assertEquals(expResult.getFirstName(), result.getFirstName());
    }

//    /**
//     * Test of getPerson method, of class PersonFacade.
//     */
    @Test
    public void testGetPersonByPhone()
    {
        System.out.println("getPersonbyphone");
        long phoneNumber = 44885522;
        Person p = pf.getPerson(1);
        Phone phone = new Phone("44885522", "home phone", p);
        p.addPhone(phone);
        pf.editPerson(p);
        Person result = pf.getPerson(phoneNumber);
        Person expResult = pf.getPerson(1);
        assertEquals(expResult, result);

    }

    /**
     * Test of editPerson method, of class PersonFacade.
     */
    @Test
    public void testEditPerson()
    {
        Person p = pf.getPerson(1);
        p.setFirstName("frans");
        Person editedPerson = pf.editPerson(p);
        Person person = new Person("frans", "hansen", "Bob@Hop.dk");
        assertEquals(editedPerson.getFirstName(), person.getFirstName());

    }

    /**
     * Test of addPerson method, of class PersonFacade.
     */
    @Test
    public void testAddPerson()
    {
        System.out.println("addPerson");

        int expResult = 4;
        pf.addPerson(new Person("Forsatan", "Larsen", "Knud@Hop.dk"));
        assertEquals(expResult, pf.getPersons().size());

    }

    /**
     * Test find persons by zip code.
     */
    @Test
    public void testGetPersonsByZip()
    {
        System.out.println("getPersonsbyzip");
        CityInfo ci = new CityInfo("9999", "HardZip");
        Address address = new Address("EnSted", "2. tv", ci);
        ci.addAddress(address);
        Person p = pf.getPerson(1);
        p.setAddress(address);
        p = pf.editPerson(p);
        System.out.println(p.getAddress().getCityInfo().getZipCode());

        String zipCode = "9999";

        Person expResult = pf.getPerson(1);
        List<Person> result = pf.getPersons(zipCode);
        assertEquals(expResult.getFirstName(), result.get(0).getFirstName());
        int size = 1;
        assertEquals(size, result.size());

    }

    /**
     * Test of deletePerson method, of class PersonFacade.
     */
    @Test
    public void testDeletePerson_Person()
    {
        System.out.println("deletePerson");
        pf.deletePerson(1);
        assertEquals(2, pf.getPersons().size());
        ;
    }

}
