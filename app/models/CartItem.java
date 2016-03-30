package models;

import java.util.*;

import javax.persistence.*;

import java.util.Date;

import com.avaje.ebean.Model;

@Entity
public class CartItem extends Model {

	@Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    public Cart cart;

    @ManyToOne
    public Product product;

    public int quantity;
     
    public static Finder<Long,CartItem> find = new Finder<Long,CartItem>(Long.class, CartItem.class); 


}