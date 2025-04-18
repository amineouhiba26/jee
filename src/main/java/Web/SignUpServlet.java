package Web;

import dao.gestionUserGpa;
import entities.Role;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private gestionUserGpa userService;

    @Override
    public void init() throws ServletException {
        userService = new gestionUserGpa();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        // Display sign-up form
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // Create new user
        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(password);

        // Assign default role "user"
        Role userRole = new Role();
        userRole.setId(2); // You must ensure this
        // role exists in the DB or is being handled properly
        newUser.setRole(userRole);

        try {
            userService.addUser(newUser);
            response.sendRedirect("login");
        } catch (Exception e) {
            request.setAttribute("error", "Échec de l'inscription. Réessayez.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }
}
