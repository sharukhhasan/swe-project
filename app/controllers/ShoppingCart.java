package controllers;

import play.*;

import java.util.*;

import play.mvc.*;
import models.*;
import models.CartItem;

import play.data.Form;
import play.data.DynamicForm;
import com.avaje.ebean.*;
import Util.SessionHandling;

import views.html.*;

public class ShoppingCart extends Controller {

	public Result shoppingCart()
    {
    	User user = SessionHandling.getUser();
    	if (user == null) {
    		return redirect(controllers.routes.Error.error("User is not logged in"));
    	}
    	Cart cart = getCurrentCart();
    	if (cart == null) {
    		return redirect(controllers.routes.Error.error("Could not get cart"));
    	}
    	cart = Ebean.find(Cart.class)
    			.select("*")
    			.fetch("items")
    			.fetch("items.product")
    			.where().eq("id", cart.id)
    			.findUnique();
    			
    	System.out.println("Succesfully got cart id: " + cart.id);
    	List<CartItem> items = cart.items;
    	if (items == null || items.size() == 0) {
    		return redirect(controllers.routes.Error.error("Could not get cart items"));
    	}
    	System.out.println("Succesfully got cart items ");
    	Double total = calculateTotal();
    	System.out.println("Succesfully calculated total: " + total);
    	
        return ok(shoppingcart.render(cart.items, total));
    }
	
	public Double calculateTotal() {
		Double total = 0.0;
		Cart cart = getCurrentCart();
		cart = Ebean.find(Cart.class)
    			.select("*")
    			.fetch("items")
    			.fetch("items.product")
    			.where().eq("id", cart.id)
    			.findUnique();
		for(CartItem item : cart.items){
			total = total + item.product.productPrice * item.quantity;
		}
		return total;
	}

    public Cart getCurrentCart() {
    	User user = SessionHandling.getUser();
		
		List<Cart> carts = Ebean.find(Cart.class)
				.where().eq("userId", user.id)
				.findList();
		
		Cart cart = new Cart();
		cart.userId = user.id;
		if (carts == null) {
			Ebean.save(cart);
			return cart;
		}
		
		if (carts.size() != 0) {
			for(Cart userCart : carts) {
				if(!userCart.purchased) {
					cart = userCart;
					break;
				}
			}
		}
		Ebean.save(cart);
		return cart;
    }

	public Result addItems() {
		
		Cart cart = getCurrentCart();
		
		DynamicForm requestData = DynamicForm.form().bindFromRequest();
		Map<String, String> formEntries = requestData.data();

		for(Map.Entry<String, String> entry : formEntries.entrySet()) {
			String productId = entry.getKey();
    		String productQuantity = entry.getValue();
    		CartItem cartItem = new CartItem();
    		cartItem.product = Product.find.byId(Long.valueOf(productId).longValue());
    		if(!productQuantity.equals("")) {
    			try {
    				cartItem.quantity = Integer.valueOf(productQuantity);
    				cart.items.add(cartItem);
    				cartItem.cart = cart;
    				Ebean.save(cartItem);
    				System.out.println("adding item # " + productId + " to cart, quantity: " + productQuantity);
    			}
    			catch (NumberFormatException e) {
    				System.out.println("Not a valid quantity!");
    			}   			
    			
    		}
    		
		}
	
		Ebean.save(cart);
		return redirect(controllers.routes.ShoppingCart.shoppingCart());
		
	}

}