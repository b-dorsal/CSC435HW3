package retailservice.restful.controller;

import retailservice.model.Store;
import retailservice.restful.view.StoreView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.BufferedReader;
import org.json.*;

public class StoreController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String id = req.getParameter("id");

        res.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            try {
                Store store = Store.getStore(id);
                if(store != null){
                    out.print(StoreView.getView(store));
                }else{
                    out.print(StoreView.getView(null));
                }

            } catch (Exception e) {
                res.setStatus(500);
                out.print(StoreView.getView(null));
            }
            out.close();
        } catch (IOException e) {
            System.out.println("failerr");
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
                JSONObject requestStore = new JSONObject(jsonObject.getString("Method"));
                Store store = new Store(requestStore.getString("storeid"), requestStore.getString("established"), requestStore.getString("address"), requestStore.getString("city"), requestStore.getString("state"), requestStore.getString("manager"), requestStore.getInt("numEmployees"), requestStore.getInt("size"));
                Store.addStore(store);
                out.print(StoreView.getView(Store.getStore(store.getStoreID())));
            } catch (JSONException e) {
                res.setStatus(500);
                out.print(StoreView.getView(null));
                throw new IOException("Error parsing JSON request string");
            }
        }catch (IOException e){
            System.out.println("failerr");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        final String id = req.getParameter("id");
        res.setContentType("application/json; charset=UTF-8");
        try (PrintWriter out = res.getWriter()) {
            try {
                Store.deleteStore(id);
//                out.print();
                res.setStatus(200);
            } catch (Exception e) {
                res.setStatus(500);
                out.print(StoreView.getView(null));
            }
            out.close();
        } catch (IOException e) {
            res.setStatus(500);
        }
    }

}
