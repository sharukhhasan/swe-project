package controllers;

import play.*;

import java.util.*;

import play.mvc.*;
import models.*;
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

    public Result purchaseItems() {
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
        cart.purchased = true;
        Date date = new Date();
        cart.purchaseDate = date;
        Double total = calculateTotal();
        List<CartItem> items = cart.items;
        if (items == null || items.size() == 0) {
            return redirect(controllers.routes.Error.error("Could not get cart items"));
        }
        for(CartItem item : items) {
            int qty = item.product.productQuantity - item.quantity;
            System.out.println("Setting new qty for " + item.product.productName + " quantity: " + qty);
            item.product.productQuantity = qty;
            //Product p = Product.find.byId(item.product.id);
            Ebean.save(item.product);
        }
        Ebean.save(cart);
        return ok(genericLander.render("Purchase Success!", "Your purchase of $" + total + "has been made succesfully!"));

    }
    
    public Result showOrderPage() {
    	List<Cart> orders = getPastOrders();
    	
    	List <Cart> ordersToRemove = new ArrayList<Cart>(); 
    	for(Cart c : orders) {
    		if(c.items.size() == 0) {
    			ordersToRemove.add(c);
    		} else {
    			List<CartItem> itemsToRemove = new ArrayList<CartItem>();
    			for(CartItem item : c.items){
    				if(item.quantity == 0) {
    					itemsToRemove.add(item);
    				}
    			}
    			for(CartItem item : itemsToRemove) {
    				c.items.remove(item);
    				if(c.items.size() == 0) {
						ordersToRemove.add(c);
					}
    			}
    		}
    	}
    	for (Cart c : ordersToRemove) {
    		orders.remove(c);
    	}
    	
    	if(orders.size() == 0 ) {
            return ok(genericLander.render("No orders", "No orders have been made by user!"));
    	}
    	return ok(vieworders.render(orders));
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
	
	
	public List<Cart> getPastOrders() {
		User user = SessionHandling.getUser();
		List<Cart> carts = Ebean.find(Cart.class)
				.select("*")
    			.fetch("items")
    			.fetch("items.product")
				.where()
				.conjunction()
					.eq("userId", user.id)
					.eq("purchased", true)
				.endJunction()
				.findList();
		return carts;
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