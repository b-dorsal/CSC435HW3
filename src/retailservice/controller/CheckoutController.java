package retailservice.controller;

import retailservice.model.Product;
import retailservice.model.Sale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CheckoutController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher rd;

        HttpSession session = request.getSession(false);
        int addSku = 0;
        if(request.getParameter("sku") != null){
            addSku = Integer.parseInt(request.getParameter("sku"));
        }


        final Product foundProduct = Product.getProduct(addSku);

        int saleID;
        if(session.getAttribute("saleid") != null){
            saleID = (int) session.getAttribute("saleid");
        }else{
            saleID = ThreadLocalRandom.current().nextInt(100000, 1000000);
        }

        List<Product> checkoutList = (ArrayList<Product>) session.getAttribute("checkoutList");
        if(checkoutList == null){
            checkoutList = new ArrayList<>();
        }

        System.out.println(saleID);


        if(foundProduct != null){
            checkoutList.add(foundProduct);
            rd = request.getRequestDispatcher("../checkout.jsp");
        }else{
            rd = request.getRequestDispatcher("../checkout.jsp");
        }

        session.setAttribute("checkoutList", checkoutList);
        session.setAttribute("saleid", saleID);
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        RequestDispatcher rd;
        HttpSession session = request.getSession(false);

        List<Product> checkoutList = (ArrayList<Product>) session.getAttribute("checkoutList");

        if(checkoutList == null){
            rd = request.getRequestDispatcher("../checkout.jsp");
        }else{
            if(session.getAttribute("saleid") != null){
                int saleID = (int) session.getAttribute("saleid");
                System.out.println("HERE1");
                if(session.getAttribute("checkoutList") != null){
                    System.out.println("HERE2");
                    Sale.postSales(saleID, (ArrayList<Product>) session.getAttribute("checkoutList"));
                }
                System.out.println("ERR");
            }
            rd = request.getRequestDispatcher("/../checkoutsuccess.jsp");
        }

        session.setAttribute("checkoutList", null);
        rd.forward(request, response);
    }

}

