package dao;

import entities.Role;
import entities.User;

import javax.persistence.*;
import java.util.List;

public class gestionUserGpa implements IGestionUser {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogue");

    @Override
    public void addUser(User u) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(u);
            et.commit();

    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT u FROM User u");
            return q.getResultList();

    }

    @Override
    public User getUser(int id) {
        EntityManager em = emf.createEntityManager();
            return em.find(User.class, id);

    }

    @Override
    public void updateUser(User u) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(u);
            et.commit();

    }

    @Override
    public void deleteUser(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

            et.begin();
            User u = em.find(User.class, id);
            if (u != null) {
                em.remove(u);
            }
            et.commit();

    }

    @Override
    public boolean isAdmin(User u) {
        return u.getRole() != null && "admin".equalsIgnoreCase(u.getRole().getName());
    }


    @Override
    public void makeAdmin(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        try {
            et.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                Role adminRole = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                    .setParameter("name", "admin")
                    .getSingleResult();
                user.setRole(adminRole);
                em.merge(user);
            }
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) et.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
