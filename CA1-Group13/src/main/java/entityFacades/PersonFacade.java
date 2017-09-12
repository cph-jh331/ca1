package entityFacades;

import entity.Address;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class PersonFacade implements IPersonFacade {

    private EntityManagerFactory emf;

    public PersonFacade() {
    }
    
    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public PersonFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override
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

    @Override
    public Person getPerson(long phoneNumber)
    {
        String phone = "" + phoneNumber;
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Person.findPersonByPhone");
            q.setParameter("phoneNumber", phone);
            return (Person) q.getSingleResult();

        } finally
        {
            em.close();
        }
    }

    @Override
    public Person editPerson(Person p)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Person deletePerson(int id)
    {
        EntityManager em = getEntityManager();
        long lid = id;
        try
        {
            Person p = em.find(Person.class, lid);
            if (p != null)
            {
                em.getTransaction().begin();
                em.remove(p);
                em.getTransaction().commit();
                return p;
            }
            return null;
        } finally
        {
            em.close();
        }
    }

    @Override
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

    @Override
    public List<Person> getPersons()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("InfoEntity.findAllPersons");
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Person> getPersons(String zipCode)
    {
        EntityManager em = getEntityManager();

        try
        {
            Query q = em.createNamedQuery("InfoEntity.findPersonsByZip");
            q.setParameter("zipcode", zipCode);
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

    @Override
    public Person deletePerson(Person person)
    {
        EntityManager em = getEntityManager();
        try
        {
            Person p = em.find(Person.class, person.getId());
            if (p != null)
            {
                em.getTransaction().begin();
                em.remove(person);
                em.getTransaction().commit();
                return p;
            }
            return null;
        } finally
        {
            em.close();
        }
    }

}
