package Web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.GestionCategory;
import dao.IGestionCategorie;
import dao.gestionProduitGpa;
import dao.IGestionProduit;
import entities.Categorie;
import entities.Produit;

@WebServlet("/Controlleur")
public class Controlleur extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IGestionProduit gestion;
    private IGestionCategorie gestionCategorie;

    @Override
    public void init() throws ServletException {
        gestion = new gestionProduitGpa();
        gestionCategorie = new GestionCategory();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String mc = request.getParameter("mc");
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");
        String view = request.getParameter("view");

        // Always fetch categories for product-related pages
        List<Categorie> categoriesList = gestionCategorie.getCategories();
        request.setAttribute("categories", categoriesList);

        switch (action != null ? action : "") {
            case "delete": {
                if (idParam != null) {
                    int id = Integer.parseInt(idParam);
                    gestion.deleteProduct(id);
                }
                response.sendRedirect("Controlleur");
                return;
            }

            case "edit": {
                if (idParam != null) {
                    int id = Integer.parseInt(idParam);
                    Produit produit = gestion.getProduct(id);
                    request.setAttribute("productToEdit", produit);
                    request.setAttribute("isEdit", true);
                }
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                return;
            }

            case "addToCart": {
                if (idParam != null) {
                    int id = Integer.parseInt(idParam);
                    Produit produitToAdd = gestion.getProduct(id);

                    List<Produit> cart = (List<Produit>) session.getAttribute("cart");
                    if (cart == null) {
                        cart = new ArrayList<>();
                    }

                    boolean exists = cart.stream().anyMatch(p -> p.getId() == produitToAdd.getId());
                    if (!exists) {
                        cart.add(produitToAdd);
                    }

                    session.setAttribute("cart", cart);
                }
                response.sendRedirect("Controlleur");
                return;
            }

            case "viewCart": {
                List<Produit> cartView = (List<Produit>) session.getAttribute("cart");
                if (cartView == null) cartView = new ArrayList<>();
                request.setAttribute("cart", cartView);
                request.getRequestDispatcher("panier.jsp").forward(request, response);
                return;
            }

            case "removeFromCart": {
                if (idParam != null) {
                    int id = Integer.parseInt(idParam);
                    List<Produit> currentCart = (List<Produit>) session.getAttribute("cart");
                    if (currentCart != null) {
                        currentCart.removeIf(p -> p.getId() == id);
                    }
                    session.setAttribute("cart", currentCart);
                }
                response.sendRedirect("Controlleur?action=viewCart");
                return;
            }

            case "addProduct": {
                request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                return;
            }

            default: {
                List<Produit> produits = (mc != null && !mc.trim().isEmpty()) ?
                    gestion.getProductsByMc(mc) :
                    gestion.getAllProducts();

                request.setAttribute("products", produits);
                request.setAttribute("mc", mc);

                if ("addProduct.jsp".equals(view)) {
                    request.getRequestDispatcher("addProduct.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("accueil.jsp").forward(request, response);
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prix = request.getParameter("prix");
        String quantite = request.getParameter("quantite");
        String categorieId = request.getParameter("categorieId");

        if (nom != null && prix != null && quantite != null && categorieId != null && !categorieId.isEmpty()) {
            try {
                Produit produit = new Produit();
                produit.setNom(nom);
                produit.setPrix(Double.parseDouble(prix));
                produit.setQuantite(Integer.parseInt(quantite));

                int catId = Integer.parseInt(categorieId);
                Categorie categorie = gestionCategorie.getCategorie(catId);
                produit.setCategorie(categorie);

                if (idParam != null && !idParam.trim().isEmpty()) {
                    produit.setId(Integer.parseInt(idParam));
                    gestion.updateProduct(produit);
                } else {
                    gestion.addProduct(produit);
                }

                response.sendRedirect("Controlleur");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("Controlleur?action=addProduct&error=format");
            }
        } else {
            response.sendRedirect("Controlleur?action=addProduct&error=missing");
        }
    }
}
