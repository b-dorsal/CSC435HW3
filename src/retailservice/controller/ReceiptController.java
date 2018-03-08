package retailservice.controller;

import retailservice.model.Sale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ReceiptController extends  HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher rd;
        HttpSession session = request.getSession(false);
        int requestSale = Integer.parseInt(request.getParameter("receipt"));

        final List<Sale> foundSales = Sale.getSale(requestSale);

        if (foundSales.get(0) != null) {
            session.setAttribute("receipts", foundSales);
            rd = request.getRequestDispatcher("../receipt.jsp");
        } else {
            rd = request.getRequestDispatcher("../findreceipt.jsp");
        }

        rd.forward(request, response);
    }
}
