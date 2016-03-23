package db;

import models.Product;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


/**
 * Created by Sharukh on 3/22/16.
 */
public class DatabaseHelper {
    public static final String query = "select * from products";
    public static final String dbConnect = "jdbc:postgresql://localhost:5432/Sharukh";
    public static ResultSet rs = null;
    public static Statement statement = null;
    public static Connection connection = null;
    public static List<Product> productList = new ArrayList<>();

    public static List<Product> getProductsFromDB()
    {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(dbConnect, "Sharukh", "Coldwell11!");
            if(connection != null)
            {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while(rs.next())
                {
                    Product product = new Product();
                    product.productName = rs.getString("product_name");
                    product.productDescription = rs.getString("product_description");
                    product.productQuantity = rs.getInt("product_quantity");
                    product.productManufacturer = rs.getString("product_manufacturer");
                    product.productPrice = rs.getInt("product_price");
                    productList.add(product);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return productList;
    }
}
