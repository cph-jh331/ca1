/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityFacades;

import entity.CityInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Lasse Andersen
 */
public class CityInfoFacade implements ICityInfoFacade {

    private EntityManagerFactory emf;

    public CityInfoFacade(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    @Override
    public CityInfo getCityInfo(String zipcode)
    {
        EntityManager em = getEntityManager();

        try
        {
            em.getTransaction().begin();
            CityInfo ci = em.find(CityInfo.class, zipcode);
            em.getTransaction().commit();
            return ci;

        } finally
        {
            em.close();

        }

    }

    @Override
    public CityInfo addCityInfo(CityInfo cityInfo)
    {
        EntityManager em = getEntityManager();

        try
        {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
            return cityInfo;

        } finally
        {
            em.close();
        }

    }

    @Override
    public CityInfo editCityInfo(CityInfo cityInfo)
    {
        EntityManager em = getEntityManager();

        try
        {

            em.getTransaction().begin();
            CityInfo ci = em.find(CityInfo.class, cityInfo.getZipCode());
            if (ci != null)
            {
                em.merge(ci);
            }
            em.getTransaction().commit();
            return ci;

        } finally
        {
            em.close();
        }
    }

    @Override
    public CityInfo deleteCityInfo(CityInfo cityInfo)
    {
        EntityManager em = getEntityManager();

        try
        {
            em.getTransaction().begin();
            CityInfo ci = em.find(CityInfo.class, cityInfo.getZipCode());
            if (ci != null)
            {
                em.remove(ci);
            }
            em.getTransaction().commit();
            return ci;

        } finally
        {
            em.close();
        }
    }

    @Override
    public CityInfo deleteCityInfo(String zipCode)
    {
        EntityManager em = getEntityManager();

        try
        {
            CityInfo ci = em.find(CityInfo.class, zipCode);
            em.getTransaction().begin();
            if (zipCode != null)
            {
                em.remove(ci);
            }
            em.getTransaction().commit();
            return ci;
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<CityInfo> getAllCityInfos()
    {
        EntityManager em = getEntityManager();

        try
        {
            Query query = em.createNamedQuery("CityInfo.findAllCityInfo");
            return query.getResultList();

        } finally
        {
            em.close();
        }
    }

}
