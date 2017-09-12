package main;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Person;
import entity.Phone;
import entityFacades.CompanyFacade;
import entityFacades.PersonFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PopulatePerson {

    public static void main(String[] args)
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("devPU");
        EntityManager em = emf.createEntityManager();
        PersonFacade facade = new PersonFacade(emf);
        Person p = new Person("bob", "bobsen", "bob@bob.dk");
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
        em = emf.createEntityManager();
        List<Person> ps = facade.getPersons("0800");
        for (Person p1 : ps)
        {
            System.out.println(p1.getFirstName());
        }

        p = new Person("Hans", "Hansen", "hans@hansen.dk");
        p = facade.addPerson(p);
        ci = em.find(CityInfo.class, "0900");
        adr = new Address("Hansvej", "5. tv", ci);
        ci.addAddress(adr);
        p.setAddress(adr);
        facade.editPerson(p);
        em.close();
        CompanyFacade cf = new CompanyFacade(emf);
        Company com = new Company("PelsCO", "Sælger pels", 33004433, 40, 2342500.43, "pels@pelsco.dk");
        cf.addCompany(com);

        p = facade.getPerson(1);
        Phone phone = new Phone("44885522", "home phone", p);
        em = emf.createEntityManager();
        em.getTransaction().begin();
        p.addPhone(phone);
        em.merge(p);
        em.getTransaction().commit();
        em.close();

        p = facade.getPerson(44885522L);
        System.out.println(p.getFirstName());

    }
}
