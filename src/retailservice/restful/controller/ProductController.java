package retailservice.restful.controller;

import retailservice.model.Product;
import retailservice.restful.view.ProductView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedReader;
import org.json.*;


public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final int sku = Integer.parseInt(req.getParameter("sku"));

        res.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            try {
                Product product = Product.getProduct(sku);
                if(product != null){
                    out.print(ProductView.getView(product));
                }else{
                    out.print(ProductView.getView(null));
                }

            } catch (Exception e) {
                res.setStatus(500);
                out.print(ProductView.getView(null));
            }
            out.close();
        } catch (IOException e) {
            res.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("application/json; charset=UTF-8");
        StringBuffer jb = new StringBuffer();
        String line = null;
        try (PrintWriter out = res.getWriter()) {
            try {
                BufferedReader reader = req.getReader();
                while ((line = reader.readLine()) != null)
                    jb.append(line);
            } catch (Exception e) {
                res.setStatus(500);
            }
            try {

                JSONObject jsonObject = HTTP.toJSONObject(jb.toString());
                JSONObject requestProduct = new JSONObject(jsonObject.getString("Method"));

                Product product = new Product(Integer.parseInt(requestProduct.getString("sku")), requestProduct.getString("name"), Integer.parseInt(requestProduct.getString("stock")), Float.parseFloat(requestProduct.getString("price")), requestProduct.getString("desc"), Float.parseFloat(requestProduct.getString("rating")), requestProduct.getString("type"));
                Product.putProduct(product);
                out.print(ProductView.getView(Product.getProduct(product.getSku())));
            } catch (JSONException e) {
                e.printStackTrace();
                res.setStatus(500);
                out.print(ProductView.getView(null));
                throw new IOException("Error parsing JSON request string");
            }
        }catch (IOException e){
            res.setStatus(500);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final int sku = Integer.parseInt(req.getParameter("sku"));
        res.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            try {
                Product.deleteProduct(sku);
//                out.print();
                res.setStatus(200);
            } catch (Exception e) {
                res.setStatus(500);
                out.print(ProductView.getView(null));
            }
            out.close();
        } catch (IOException e) {
            res.setStatus(500);
        }
    }

}
