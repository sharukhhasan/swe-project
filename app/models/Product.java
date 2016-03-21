package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    public int quantity;
    public String manufacturer;
    public int price;
    @Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date datePosted;
}
