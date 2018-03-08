package retailservice.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import retailservice.model.Store;

public class StoreController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher rd;

        String requestStore = request.getParameter("id");

        final Store foundStore = Store.getStore(requestStore);
        request.setAttribute("store", foundStore);

        if(foundStore != null){
            rd = request.getRequestDispatcher("../store.jsp");
        }else{
            rd = request.getRequestDispatcher("../findstore.jsp");
        }

        rd.forward(request, response);
    }

}
