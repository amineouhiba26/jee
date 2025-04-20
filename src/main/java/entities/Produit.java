package entities;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private double prix;
    private int quantite;
    @Transient
    private int orderedQuantity = 1;


    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = true)
    private Categorie categorie;

    public Produit() {}

    public Produit(int id, String nom, double prix, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    // Getters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public int getQuantite() { return quantite; }
    public Categorie getCategorie() { return categorie; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public void setCategorie(Categorie categorie) { this.categorie = categorie; }
    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(int orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public double getTotalPrice() {
        return this.prix * this.orderedQuantity;
    }
}
