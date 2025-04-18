package dao;

import entities.Categorie;

import javax.persistence.*;
import java.util.List;

public class GestionCategory implements IGestionCategorie {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogue");

    @Override
    public void addCategorie(Categorie c) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(c);
        tx.commit();
    }

    @Override
    public List<Categorie> getCategories() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Categorie c");
        return query.getResultList();
    }

    @Override
    public Categorie getCategorie(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Categorie.class, id);
    }

    @Override
    public void deleteCategorie(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Categorie c = em.find(Categorie.class, id);
        if (c != null) {
            em.remove(c);
        }
        tx.commit();
    }

    @Override
    public void updateCategorie(Categorie updatedCategorie) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Categorie existingCategorie = em.find(Categorie.class, updatedCategorie.getId());
            if (existingCategorie != null) {
                existingCategorie.setNom(updatedCategorie.getNom());
                // if you add more fields in the future, update them here too
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}
