package dao;

import entities.Categorie;
import entities.Produit;

import java.util.List;

public interface IGestionCategorie {

    void addCategorie(Categorie c);
    List<Categorie> getCategories();
    Categorie getCategorie(int id);
    void deleteCategorie(int id);


}
