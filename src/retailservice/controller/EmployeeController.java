package retailservice.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import retailservice.model.Employee;

public class EmployeeController extends HttpServlet {

    public EmployeeController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd;

        String requestID = request.getParameter("empid");

        final Employee foundEmployee = Employee.getEmployee(requestID);
        request.setAttribute("employee", foundEmployee);

        if(foundEmployee != null){
            rd = request.getRequestDispatcher("/employee.jsp");
        }else{
            rd = request.getRequestDispatcher("/findemployee.jsp");
        }

        rd.forward(request, response);
    }
}
