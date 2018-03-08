package retailservice.controller;

import java.io.IOException;
import java.io.*;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.*;
import retailservice.model.Product;
import retailservice.model.Store;

public class ProductController extends HttpServlet {

    public ProductController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;

        int requestProduct = Integer.parseInt(request.getParameter("sku"));

        final Product foundProduct = Product.getProduct(requestProduct);
        request.setAttribute("product", foundProduct);

        if(foundProduct != null){
            rd = request.getRequestDispatcher("/product.jsp");
        }else{
            rd = request.getRequestDispatcher("/findproduct.jsp");
        }

        rd.forward(request, response);
    }
}
