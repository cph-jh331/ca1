package entityFacades;

import entity.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class CompanyFacade implements ICompanyFacade {

    private EntityManagerFactory emf;

    public CompanyFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override
    public Company getCompany(Long cvr)
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Company.findCompWithCVR");
            q.setParameter("cvr", cvr);
            Company c = (Company) q.getSingleResult();
            return c;
        } catch (NoResultException e)
        {
            return null;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Company addCompany(Company c)
    {
        EntityManager em = getEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Company getCompany(int id)
    {
        EntityManager em = getEntityManager();
        long lid = id;
        try
        {
            return em.find(Company.class, lid);
        } finally
        {
            em.close();
        }
    }

    @Override
    public Company editCompany(Company c)
    {
        EntityManager em = getEntityManager();
        try
        {
            Company comp = em.find(Company.class, c.getId());
            if (comp != null)
            {
                em.getTransaction().begin();
                em.merge(c);
                em.getTransaction().commit();
            }
            return comp;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Company deleteCompany(int id)
    {
        EntityManager em = getEntityManager();
        long lid = id;
        try
        {
            //skal spørge om begin transaction.
            em.getTransaction().begin();
            Company c = em.find(Company.class, lid);
            if (c != null)
            {
                em.remove(c);
            }
            em.getTransaction().commit();
            return c;

        } finally
        {
            em.close();
        }
    }

    @Override
    public Company deleteCompany(Long cvr)
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Company.findCompWithCVR")
                    .setParameter("cvr", cvr);
            Company c = (Company) q.getSingleResult();
            if (c != null)
            {
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
            }
            return c;
        } catch (NoResultException e)
        {
            return null;
        } finally
        {
            em.close();
        }
    }

    @Override
    public Company deleteCompany(Company c)
    {
        EntityManager em = getEntityManager();
        try
        {
            Company comp = em.find(Company.class, c.getId());
            if (comp != null)
            {
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
            }
            return comp;
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<Company> getCompanies()
    {
        EntityManager em = getEntityManager();
        try
        {
            Query q = em.createNamedQuery("Company.findAllCompanies");
            return q.getResultList();
        } finally
        {
            em.close();
        }
    }

}
