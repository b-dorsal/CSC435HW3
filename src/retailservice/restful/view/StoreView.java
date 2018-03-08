package retailservice.restful.view;

import org.json.JSONObject;
import retailservice.model.Store;

public class StoreView {

    public static String getView(final Store store) {
        JSONObject json = new JSONObject(store);
        return json.toString();
    }
}
