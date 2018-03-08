package retailservice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import retailservice.model.Authenticate;
import retailservice.model.User;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            Authenticate auth = new Authenticate();
            User user = null;
            if (auth.authenticate(username, password)) {
                user = User.getUser(username);
            }

            if (user != null) {
                request.getSession().setAttribute("user", user);
                response.sendRedirect(request.getContextPath() + "/manage/Profile");
                return;
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

}