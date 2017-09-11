package entityFacades;

import entity.CityInfo;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class EntityFacade {

    private EntityManagerFactory emf;

    public EntityFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public Person getPerson(int id)
    {
        long lid = id;
        EntityManager em = getEntityManager();

        try
        {
            em.getTransaction().begin();
            Person p = em.find(Person.class, lid);
            em.getTransaction().commit();
            return p;

        } finally
        {
            em.close();
        }
    }

    public Person addPerson(Person p)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally
        {
            em.close();
        }
    }

    public List<Person> getPersons()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("InfoEntity.findAllPerson");
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    public List<Person> getPersons(String zipCode)
    {
        EntityManager em = getEntityManager();

        try
        {
            Query q = em.createNamedQuery("InfoEntity.findAllByZipcode");
            
            System.out.println(q);
            return null;
        } finally
        {
            em.close();
        }
    }

}
