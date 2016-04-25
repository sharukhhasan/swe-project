package controllers;

import play.*;

import java.util.*;

import jdk.nashorn.internal.objects.annotations.Where;
import play.mvc.*;
import models.*;
import play.data.Form;
import play.data.DynamicForm;

import com.avaje.ebean.*;

import Util.SessionHandling;
import views.html.*;

public class Return extends Controller {
	
	public Result returnScreen(Long itemId) {
		CartItem item = Ebean.find(CartItem.class)
			.select("*")
			.fetch("product")
			.where().eq("id", itemId)
			.findUnique();
		Integer inStockQuantity = item.product.productQuantity;
		List<Product> relatedProducts = Ebean.find(Product.class)
							.where()
							.eq("productCategory", item.product.productCategory)
							.findList();
	    return ok(returnItem.render(item, inStockQuantity, relatedProducts));
	}
	
	public Result exchangeItem(Long itemId) {
		CartItem item = Ebean.find(CartItem.class)
				.select("*")
				.fetch("product")
				.where().eq("id", itemId)
				.findUnique();
		
		DynamicForm requestData = DynamicForm.form().bindFromRequest();
		Map<String, String> formEntries = requestData.data();
		Integer exchangeItemQty = Integer.parseInt(formEntries.get("exchangeItemQty"));
		formEntries.remove("exchangeItemQty");
		if(item.returnedQuantity + exchangeItemQty > item.quantity) {
			return redirect(controllers.routes.Error.error("Trying to return too many items!"));
		}
		item.returnedQuantity = item.returnedQuantity + exchangeItemQty;
		item.returned = true;
		Ebean.save(item);
		
		Double exchangeCredit = exchangeItemQty * item.product.productPrice; 
		
		Cart c = new Cart();
		Ebean.save(c);
		Double total = 0.0;
		System.out.println("[LOG DEBUG]: Return quantity is: " + exchangeItemQty);
		for(Map.Entry<String, String> entry : formEntries.entrySet()) {
			System.out.println("[LOG DEBUG]: Key" + entry.getKey() + " Value: " + entry.getValue());
		}
		for(Map.Entry<String, String> entry : formEntries.entrySet()) {
			String productId = entry.getKey();
			if(productId != null && productId != "") {
				if(entry.getValue() != "") {
					Product p = Ebean.find(Product.class)
							.where().eq("id", Long.parseLong(entry.getKey()))
							.findUnique();
					CartItem cartItem = new CartItem();
					cartItem.product = p;
					cartItem.quantity = Integer.parseInt(entry.getValue());
					cartItem.cart = c;
					total = total + (p.productPrice * cartItem.quantity);
					c.items.add(cartItem);
					p.productQuantity = p.productQuantity - cartItem.quantity;
					Ebean.save(p);
					Ebean.save(cartItem);
				}
			} else {
				return redirect(controllers.routes.Error.error("Problem parsing product ids..."));
			}
			
		}
		Date date = new Date();
		c.purchased = true;
		c.purchaseDate = date;
		Long userId = SessionHandling.getUser().id; 
		c.userId = userId;
		c.total = total;
		Ebean.save(c);
		
		Double balance = exchangeCredit - total;
		
		if (balance == 0) {
			return ok(genericLander.render("Exchange Success!", "Your exchange is complete!"));
		}
		return ok(genericLander.render("Exchange Success!", "A balance of $" + balance + " has been credited to your account."));
	}
	
	public Result returnItem(Long itemId) {
		DynamicForm requestData = DynamicForm.form().bindFromRequest();
		Map<String, String> formEntries = requestData.data();
		String returnQtyString = formEntries.get("returnItemQty");
		Integer returnQty = -1;
		try {
			returnQty = Integer.parseInt(returnQtyString); 
		} catch(NumberFormatException e) {
			return redirect(controllers.routes.Error.error("User input not valid: " + returnQtyString));
		}
		if(returnQty < 0) {
			return redirect(controllers.routes.Error.error("User input not valid: " + returnQtyString));
		}
		
		CartItem item = Ebean.find(CartItem.class)
				.select("*")
				.fetch("product")
				.where().eq("id", itemId)
				.findUnique();
		if(item.returnedQuantity + returnQty > item.quantity) {
			return redirect(controllers.routes.Error.error("All items have been returned already"));
		}
		item.product.productQuantity = item.product.productQuantity + returnQty; 
		Ebean.save(item.product);
		item.returned = true;
		item.returnedQuantity = item.returnedQuantity + returnQty;
		Ebean.save(item);
		Double returnTotal = item.product.productPrice * returnQty;
		return ok(genericLander.render("Return Success!", "Return success! A total of $" + returnTotal + "0 has been returned to your account!"));
		
	}

}