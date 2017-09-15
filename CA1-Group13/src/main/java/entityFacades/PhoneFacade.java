package entityFacades;

import entity.Phone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class PhoneFacade implements IPhoneFacade {

    private EntityManagerFactory emf;

    public PhoneFacade()
    {
    }

    public PhoneFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public void addEntityManagerFactory(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    public EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override

    public Phone getPhone(String phoneNumber)
    {
        EntityManager em = getEntityManager();

        try
        {
            em.getTransaction().begin();
            Phone p = em.find(Phone.class, phoneNumber);
            em.getTransaction().commit();
            return p;

        } finally
        {
            em.close();
        }
    }

    @Override
    public Phone addPhone(Phone phone)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
            return phone;

        } finally
        {
            em.close();
        }
    }

    @Override
    public Phone editPhone(Phone phone)
    {
        EntityManager em = getEntityManager();

        try
        {
            em.getTransaction().begin();
            em.merge(phone);
            em.getTransaction().commit();
            return phone;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Phone deletePhone(Phone phone)
    {
        EntityManager em = getEntityManager();

        try
        {
            Phone p = em.find(Phone.class, phone);
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
    public Phone deletePhone(String phoneNumber)
    {
        EntityManager em = getEntityManager();
        try
        {
            Phone pNumber = em.find(Phone.class, phoneNumber);
            if (pNumber != null)
            {
                em.getTransaction().begin();
                em.remove(pNumber);
                em.getTransaction().commit();
                return pNumber;
            }
            return null;
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Phone> getPhones()
    {
        List<Phone> phoneList;
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Phone.findAllPhones");
            phoneList = query.getResultList();
            em.getTransaction().commit();
            return phoneList;

        } finally
        {
            em.close();
        }

    }

}
