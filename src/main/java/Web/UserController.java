package Web;

import dao.IGestionUser;
import dao.gestionUserGpa;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IGestionUser gestion;

    @Override
    public void init() throws ServletException {
        gestion = new gestionUserGpa();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        switch (action != null ? action : "") {
            case "delete":
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    gestion.deleteUser(id);
                }
                response.sendRedirect("UserController");
                return;

            case "edit":
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    User user = gestion.getUser(id);
                    request.setAttribute("userToEdit", user);
                    request.setAttribute("isEdit", true);
                    request.getRequestDispatcher("addUser.jsp").forward(request, response);
                }
                return;

            case "makeAdmin":
                if (idParam != null && !idParam.isEmpty()) {
                    int id = Integer.parseInt(idParam);
                    gestion.makeAdmin(id);
                }
                response.sendRedirect("UserController");
                return;

            default:
                List<User> users = gestion.getAllUsers();
                request.setAttribute("users", users);
                request.getRequestDispatcher("users.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name != null && !name.trim().isEmpty() &&
            password != null && !password.trim().isEmpty()) {

            User user = new User();
            user.setName(name);
            user.setPassword(password);

            if (idParam != null && !idParam.trim().isEmpty()) {
                user.setId(Integer.parseInt(idParam));
                gestion.updateUser(user);
            } else {
                gestion.addUser(user);
            }

            response.sendRedirect("UserController");
        } else {
            response.sendRedirect("addUser.jsp?error=missing");
        }
    }
}
