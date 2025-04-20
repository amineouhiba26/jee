package Web;

import dao.gestionUserGpa;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class LoginController extends HttpServlet {

    private gestionUserGpa userService;

    @Override
    public void init() throws ServletException {
        userService = new gestionUserGpa();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Affiche la page de connexion
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        List<User> users = userService.getAllUsers();

        for (User user : users) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if (userService.isAdmin(user)) {
                    response.sendRedirect("Controlleur"); //
                } else {
                    response.sendRedirect("Controlleur");
                }
                return;
            }
        }

        request.setAttribute("error", "Nom ou mot de passe incorrect");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }



}
