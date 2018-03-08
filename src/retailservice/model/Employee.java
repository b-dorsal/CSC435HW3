package retailservice.model;

import java.sql.*;

public class Employee{
    private String id;
    private String firstname;
    private String lastname;
    private String role;
    private String store;

    public Employee(String id, String first, String last, String role, String store){
        this.id = id;
        this.firstname = first;
        this.lastname = last;
        this.role = role;
        this.store = store;
    }

    public static Employee getEmployee(final String getID) {
        Employee employee = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees WHERE employee_id = \"" + getID + "\"");
            while(rs.next())
                employee = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            con.close();
        }catch(Exception e){ System.out.println(e);}

        return employee;
    }

    public static void putEmployee(final Employee employee) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            String query = "INSERT INTO employees VALUES(" + employee.id + ",\"" + employee.firstname + "\"," + employee.lastname + "," + employee.role + ",\"" + employee.store + "\")";
//            System.out.println(query);
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void deleteEmployee(final String deleteID) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            String query = "DELETE FROM employees WHERE employee_id = " + deleteID;
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    //    getters
    public String getID() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getRole() {
        return role;
    }

    public String getStore() {
        return store;
    }

//    setters

    public void setID(String id){
        this.id = id;
    }

    public void setFirstname(String first){
        this.firstname = first;
    }

    public void setLastname(String last){
        this.lastname = last;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setStore(String store){
        this.store = store;
    }

    @Override
    public String toString(){
        return "Employee: " + this.id + ", " + this.firstname + " " + this.lastname;
    }
}