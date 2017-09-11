/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityFacades;

import entity.Address;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lasse Andersen
 */
public class AddressFacade implements IAddressFacade {

    private EntityManagerFactory emf;

    public AddressFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Address getAddress(int id) {
        long lid = id;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Address adr = em.find(Address.class, lid);
            em.getTransaction().commit();
            return adr;
        } finally {
            em.close();
        }
    }

    @Override
    public Address addAddress(Address address) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
            return address;
        } finally {
            em.close();
        }
    }

    @Override
    public Address editAddress(Address address) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
            return address;
        } finally {
            em.close();
        }
    }

//    @Override
//    public Address deleteAddress(Address address) {
//        EntityManager em = getEntityManager();
//        if (address != null){
//            
//        try {
//            em.getTransaction().begin();
//            em.remove(address);
//            em.getTransaction().commit();
//            return address;
//
//        }
//        
//    }
//        finally {
//            em.close();
//        }
//    }

    @Override
    public Address deleteAddress(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> getAllAdresses() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
