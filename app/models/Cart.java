package models;

import java.util.*;
import javax.persistence.*;
import java.util.Date;
import play.data.format.Formats;

import com.avaje.ebean.Model;

@Entity
public class Cart extends Model {

	@Id
    @GeneratedValue
    public Long id;

    public Long userId;

    @Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date purchaseDate;

    public Double total;

    @OneToMany(mappedBy="cart")
    public List<CartItem> items;

    public Boolean purchased = false;


}