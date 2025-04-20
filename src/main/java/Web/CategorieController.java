package Web;

import dao.GestionCategory;
import dao.IGestionCategorie;
import dao.IGestionProduit;
import dao.gestionProduitGpa;
import entities.Categorie;
import entities.Produit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/categorie")
public class CategorieController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    IGestionCategorie gestion;
    IGestionProduit gestion2 ;

    @Override
    public void init() throws ServletException {
        gestion = new GestionCategory();
        gestion2 = new gestionProduitGpa() ;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if ("delete".equals(action) && idParam != null) {
            int id = Integer.parseInt(idParam);

            // First nullify the category reference in products
            List<Produit> products = gestion2.getProductsByCategory(id);
            for (Produit p : products) {
                p.setCategorie(null);
                gestion2.updateProduct(p);
            }

            // Then delete the category
            gestion.deleteCategorie(id);
            response.sendRedirect("categorie");
            return;
        }

        if ("edit".equals(action) && idParam != null) {
            int id = Integer.parseInt(idParam);
            Categorie categorie = gestion.getCategorie(id);
            request.setAttribute("categorieToEdit", categorie);
            request.setAttribute("isEdit", true);
            request.getRequestDispatcher("addCategorie.jsp").forward(request, response);
            return;
        }

        List<Categorie> categories = gestion.getCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("categories.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");

        if (nom != null && !nom.trim().isEmpty()) {
            Categorie categorie = new Categorie();
            categorie.setNom(nom);

            if (idParam != null && !idParam.trim().isEmpty()) {
                categorie.setId(Integer.parseInt(idParam));
                gestion.updateCategorie(categorie);
            } else {
                gestion.addCategorie(categorie);
            }

            response.sendRedirect("categorie");
        } else {
            response.sendRedirect("addCategorie.jsp?error=missing");
        }
    }
}
