package dao;

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
}
