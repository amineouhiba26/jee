package dao;

import entities.Produit;
import java.util.List;

public interface IGestionProduit {
    void addProduct(Produit p);
    List<Produit> getAllProducts();
    Produit getProduct(int id);
    List<Produit> getProductsByMc(String mc);
    void updateProduct(Produit p);
    void deleteProduct(int id);

    void detachProductsFromCategory(int categoryId);

    List<Produit> getProductsByCategory(int id);


}
