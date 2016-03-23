package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    public int productPrice;
    public String datePosted;
}
