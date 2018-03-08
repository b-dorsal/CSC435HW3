package retailservice.restful.view;

import org.json.JSONObject;
import retailservice.model.Employee;


public class EmployeeView {

    public static String getView(final Employee employee) {
        JSONObject json = new JSONObject(employee);
        return json.toString();
    }
}
