package retailservice.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class Store {
    private String storeID;
    private String established;
    private String address;
    private String city;
    private String state;
    private String manager;
    private int numEmployees;
    private int size;

    public Store(String storeID, String established, String address, String city, String state, String manager, int numEmployees, int size) {
        this.storeID = storeID;
        this.established = established;
        this.address = address;
        this.city = city;
        this.state = state;
        this.manager = manager;
        this.numEmployees = numEmployees;
        this.size = size;
    }

    public static Store getStore(final String id) {
        Store store = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM stores WHERE store_id = \"" + id + "\"");
            while(rs.next())
                store = new Store(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8));
            con.close();
        }catch(Exception e){ System.out.println(e);}

        return store;
    }

    public static void addStore(final Store store) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            String query = "INSERT INTO stores VALUES(\"" + store.storeID + "\",\"" + store.established + "\",\"" + store.address + "\",\"" + store.city + "\",\"" + store.state + "\",\"" + store.manager + "\"," + store.numEmployees + "," + store.size + ")";
            System.out.println(query);
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void deleteStore(final String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            System.out.println(id);
            String query = "DELETE FROM stores WHERE store_id = \"" + id + "\"";
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

// getters
    public String getStoreID() {
        return storeID;
    }

    public String getEstablished() {
        return established;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getManager() {
        return manager;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public int getSize() {
        return size;
    }

// setters
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public void setEstablished(String established) {
        this.established = established;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
