package retailservice.model;

import java.sql.*;

public class Product{
    private int sku;
    private String name;
    private int stock;
    private float price;
    private String desc;
    private float rating;
    private String type;

    public Product(int sku, String name, int stock, float price, String desc, float rating, String type){
        this.sku = sku;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.desc = desc;
        this.rating = rating;
        this.type = type;
    }

    public static Product getProduct(final int sku) {
        Product product = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products WHERE product_sku = " + sku);
            while(rs.next())
                product = new Product(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getFloat(4), rs.getString(5), rs.getFloat(6), rs.getString(8));
            con.close();
        }catch(Exception e){ System.out.println(e);}

        return product;
    }

    public static void putProduct(final Product product) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            String query = "INSERT INTO products VALUES(" + product.sku + ",\"" + product.name + "\"," + product.stock + "," + product.price + ",\"" + product.desc + "\"," + product.rating + "," + 0 + ",\"" + product.type + "\")";
//            System.out.println(query);
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static void deleteProduct(final int sku) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/retail_store","root","boo5285");
            Statement stmt = con.createStatement();
            String query = "DELETE FROM products WHERE product_sku = " + Integer.toString(sku);
            stmt.executeUpdate(query);
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

//    getters
    public int getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public float getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public float getRating() {
        return rating;
    }

    public String getType() {
        return type;
    }

//    setters

    public void setSku(int sku){
        this.sku = sku;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public void setRating(float rating){
        this.rating = rating;
    }

    public void setType(String type){
        this.type = type;
    }

    @Override
    public String toString(){
        return "Product: " + this.sku + ", " + this.name;
    }
}