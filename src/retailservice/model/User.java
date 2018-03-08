package retailservice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

    private String username;
    private String password;
    private String email;
    private String employeeID;

    public User(String username, String password, String email, String employeeID){
        this.username = username;
        this.password = password;
        this.email = email;
        this.employeeID = employeeID;
    }

    public static User getUser(final String userID) {
        User user = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE user_username = \"" + userID + "\"");
            while(rs.next())
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            con.close();
        }catch(Exception e){ System.out.println(e);}

        return user;
    }

//    public static void addUser(final User newUser) throws IOException, ParseException {

//    }



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString(){
        return "USER: " + this.username;
    }

}
