package main;

import entity.InfoEntity;
import entity.Person;
import entityFacades.PersonFacade;
import java.util.List;
import javax.persistence.Persistence;

public class hmmm {
    
    public static void main(String[] args)
    {
        PersonFacade pf = new PersonFacade(Persistence.createEntityManagerFactory("devPU"));

        Person p = pf.getPerson(1);
        System.out.println(p.getFirstName());
        System.out.println(p.getAddress());
        
    }

}
