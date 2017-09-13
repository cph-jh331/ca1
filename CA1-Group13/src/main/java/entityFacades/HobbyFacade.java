package entityFacades;

import entity.Hobby;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class HobbyFacade implements IHobbyFacade {

    private EntityManagerFactory emf;

    public HobbyFacade() {
    }

    public HobbyFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Mangler public void addEntityManager
    @Override
    public Hobby addHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
            return hobby;
        } finally {
            em.close();
        }
    }

    @Override
    public Hobby editHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
            return hobby;
        } finally {
            em.close();
        }
    }

    @Override
    public Hobby deleteHobby(Hobby hobby) {
        EntityManager em = getEntityManager();
        try {
            Hobby h = em.find(Hobby.class, hobby);
            if (h != null) {
                em.getTransaction().begin();
                em.remove(h);
                em.getTransaction().commit();
                return h;
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public Hobby deleteHobby(int id) {
        EntityManager em = getEntityManager();
        try {
            Hobby h = em.find(Hobby.class, id);
            if (h != null) {
                em.getTransaction().begin();
                em.remove(h);
                em.getTransaction().commit();
                return h;
            }
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Hobby> getAllHobbies() {
        List<Hobby> hList;
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Query query = em.createNamedQuery("Hobby.findAllHobbies");
            hList = query.getResultList();
            em.getTransaction().commit();
            return hList;
        } finally {
            em.close();
        }
    }

}
