package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Sharukh on 3/21/16.
 */
@Entity
@Table(name = "products")
public class Product extends Model {
    @Id
    @GeneratedValue
    public Long id;

    public String productName;
    public String productDescription;
    public int productQuantity;
    public String productManufacturer;
    public String productPrice;
    public int productWidth;
    public int productHeight;
    public int productLength;
    public int productWeight;
    public String productColor;
    public String datePosted;

    public static Finder<Long,Product> find = new Finder(Long.class, Product.class);

    public static List<Product> all()
    {
        return find.all();
    }

    public static Product single(Long id)
    {
        return find.byId(id);
    }

    /*public static List<Product> searchProducts()
    {
        return DatabaseHelper.getSearchResults(search);
    }*/
}
