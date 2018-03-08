package retailservice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {

    public LogoutController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;

        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null) {
            session.invalidate();
            rd = request.getRequestDispatcher("/loggedout.jsp");
        }else{
            rd = request.getRequestDispatcher("/logouterror.jsp");
        }

        rd.forward(request, response);
    }
}
