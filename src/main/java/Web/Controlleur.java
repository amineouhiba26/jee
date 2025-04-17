package Web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.gestionProduitGpa;
import dao.IGestionProduit;
import entities.Produit;

@WebServlet("/")
public class Controlleur extends HttpServlet {
    private static final long serialVersionUID = 1L;

    IGestionProduit gestion;

    @Override
    public void init() throws ServletException {
        gestion = new gestionProduitGpa();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mc = request.getParameter("mc");
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if ("delete".equals(action) && idParam != null) {
            int id = Integer.parseInt(idParam);
            gestion.deleteProduct(id);
            response.sendRedirect("Controlleur");
            return;
        }

        if ("edit".equals(action) && idParam != null) {
            int id = Integer.parseInt(idParam);
            Produit produit = gestion.getProduct(id);
            request.setAttribute("productToEdit", produit);
            request.setAttribute("isEdit", true);
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
            return;
        }

        List<Produit> liste = (mc != null && !mc.trim().isEmpty()) ?
            gestion.getProductsByMc(mc) :
            gestion.getAllProducts();

        request.setAttribute("products", liste);
        request.setAttribute("mc", mc);
        request.getRequestDispatcher("accueil.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prix = request.getParameter("prix");
        String quantite = request.getParameter("quantite");

        if (nom != null && prix != null && quantite != null) {
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(Double.parseDouble(prix));
            produit.setQuantite(Integer.parseInt(quantite));

            if (idParam != null && !idParam.trim().isEmpty()) {
                produit.setId(Integer.parseInt(idParam));
                gestion.updateProduct(produit);
            } else {
                gestion.addProduct(produit);
            }
            response.sendRedirect("Controlleur");
        } else {
            response.sendRedirect("addProduct.jsp?error=missing");
        }
    }
}
