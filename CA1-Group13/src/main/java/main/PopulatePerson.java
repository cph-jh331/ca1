package main;

import entity.Address;
import entity.CityInfo;
import entity.InfoEntity;
import entity.Person;
import entityFacades.EntityFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PopulatePerson {

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devPU");
        EntityManager em = emf.createEntityManager();
        EntityFacade facade = new EntityFacade(emf);
        Person p = new Person("bob", "bobsen", null, "bob@bob.dk", null, null);
        facade.addPerson(p);
        // We retrieve the cityInfo from the database:
        CityInfo ci = em.find(CityInfo.class, "0800");
        // we create a new address
        Address adr = new Address("gedengade", "2tv", ci);
        // we add the address to the cityInfo list
        ci.addAddress(adr);
        p.setAddress(adr);
        
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
        
        facade.getPersons("3333");
        
        

        //InfoEntity ie = new Person(firstName, lastName, hobby, email, phone, address)
    }
}
