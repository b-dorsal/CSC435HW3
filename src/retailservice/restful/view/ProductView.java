package retailservice.restful.view;

import org.json.JSONObject;
import retailservice.model.Product;


public class ProductView {

    public static String getView(final Product product) {
        JSONObject json = new JSONObject(product);
        return json.toString();
    }
}
