package dao;

import java.util.List;
import javax.persistence.*;

import entities.Produit;

public class gestionProduitGpa implements IGestionProduit {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogue");

    @Override
    public void addProduct(Produit p) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
            et.begin();
            em.persist(p);
            et.commit();

    }

    @Override
    public List<Produit> getAllProducts() {
        EntityManager em = emf.createEntityManager();

            Query q = em.createQuery("SELECT p FROM Produit p");
            return q.getResultList();

    }

    @Override
    public Produit getProduct(int id) {
        EntityManager em = emf.createEntityManager();
            return em.find(Produit.class, id);

    }

    @Override
    public List<Produit> getProductsByMc(String mc) {
        EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("SELECT p FROM Produit p WHERE p.nom LIKE :x");
            q.setParameter("x", "%" + mc + "%");
            return q.getResultList();

    }

    @Override
    public void updateProduct(Produit p) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
            et.begin();
            em.merge(p);
            et.commit();

    }

    @Override
    public void deleteProduct(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
            et.begin();
            Produit p = em.find(Produit.class, id);
            if (p != null) {
                em.remove(p);
            }
            et.commit();

    }

    @Override
    public void detachProductsFromCategory(int categoryId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
            Query query = em.createQuery(
                "UPDATE Produit p SET p.categorie = NULL WHERE p.categorie.id = :categoryId");
            query.setParameter("categoryId", categoryId);
            query.executeUpdate();
            et.commit();

    }

    @Override
    public List<Produit> getProductsByCategory(int categoryId) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Produit p WHERE p.categorie.id = :catId");
            query.setParameter("catId", categoryId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
