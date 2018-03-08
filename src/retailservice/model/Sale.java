package retailservice.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Sale{

    private int sku;
    private int id;
    private int count;
    private float price;



    public Sale(int saleID, int sku, int count, float price){
        this.id = saleID;
        this.sku = sku;
        this.count = count;
        this.price = price;
    }

    public static List<Sale> getSale(final int saleID) {
        List<Sale> sales = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sales WHERE sale_id = " + saleID);
            while(rs.next())
                sales.add(new Sale(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4)));
            con.close();
        }catch(Exception e){ System.out.println(e);}

        return sales;
    }

    public static void postSales(final int saleID, ArrayList<Product> items) {
        System.out.println("SALE " + saleID);
        System.out.println(items);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();

            for(Product p : items){
                String query = "INSERT INTO sales VALUES(" + saleID + ", " + p.getSku() + "," + 1 + "," + p.getPrice() + ")";
                stmt.executeUpdate(query);
            }

            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    //    getters
    public int getSku() {
        return sku;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    //    setters

    public void setSku(int sku){
        this.sku = sku;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setCount(int count){
        this.count = count;
    }

    @Override
    public String toString(){
        return "Sale: " + this.sku + ", " + this.id;
    }
}