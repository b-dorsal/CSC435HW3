package retailservice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import retailservice.model.User;

public class ProfileController extends HttpServlet {

    public ProfileController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;

        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            request.setAttribute("user", user);
            rd = request.getRequestDispatcher("/profile.jsp");
        }else{
            rd = request.getRequestDispatcher("/login.jsp");
        }

        rd.forward(request, response);
    }
}
