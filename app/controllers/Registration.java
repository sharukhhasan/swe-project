package controllers;

import java.util.*;
import java.security.SecureRandom;
import java.math.BigInteger;

import Util.Encryption;
import models.User;
import models.forms.UserForm;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.*;

import Util.SessionHandling;
import views.html.*;

/**
 * Created by Sharukh on 5/1/16.
 */
public class Registration extends Controller {
    public boolean isManager = false;

    private String nextToken()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(16);
    }

    public Result register()
    {
        return ok(register.render(Form.form(UserForm.class)));
    }
<<<<<<< HEAD
=======
	
	public Result requestRole() {
		DynamicForm requestData = DynamicForm.form().bindFromRequest();
		Map<String, String> formEntries = requestData.data();
		for(Map.Entry<String, String> entry : formEntries.entrySet()) {
			String key = entry.getKey();
			if(!key.equals("role")) {
				return redirect(controllers.routes.Error.error("Invalid role selection, error 1" + key));
			} else {
				String role = entry.getValue();
				if(!role.equals("manager") && !role.equals("admin") && role.equals("user")) {
					return redirect(controllers.routes.Error.error("Invalid role selection, error 2" + role));
				} else {
					User u = SessionHandling.getUser();
					if(role.equals(u.role)) {
						return ok(genericLander.render("Unable to update role", "You already have this role!"));
					}
					u.role = role;
					u.confirm_role = false;
					Ebean.save(u);
					return ok(genericLander.render("New role!", "New role " + role + " has been requested!"));
				}
			}
		}
		return redirect(controllers.routes.Error.error("Unable to update role"));
	}
	
	public Result updateUser() {
		User u = SessionHandling.getUser();
		User user = Ebean.find(User.class)
    			.select("*")
    			.fetch("address")
    			.where().eq("id", u.id)
    			.findUnique();
		Boolean hasAddress = false;
		if(user.address != null) {
			hasAddress = true;
		}
		return ok(address.render(Form.form(Address.class), user, hasAddress));
	}
	
	public Result addAddress() {
		Form<Address> form = Form.form(Address.class).bindFromRequest();
        Address address = form.get();
        User user = SessionHandling.getUser();
        address.user = user;
        user.address = address;
        Ebean.save(user);
        Ebean.save(address);
        return ok(genericLander.render("Address Updated!", "Your address has been updated!"));
	}
>>>>>>> parent of b48b893... managers unable to buy items, users not in pending roles, ui changes

    public Result addUser()
    {
        Form<UserForm> form = Form.form(UserForm.class).bindFromRequest();
        UserForm userForm = form.get();
        User user = userForm.formToUser(userForm);

        if(!userForm.password.equals(userForm.confirmPassword))
        {
            return redirect(controllers.routes.Error.error(userForm.password + " Does not match " + userForm.confirmPassword));
        }

        List<User> userResult = Ebean.find(User.class)
                .where().like("email", user.email)
                .findList();

        if(userResult.size() > 0)
        {
            return redirect(controllers.routes.Error.error("User exists for: " + user.email));
        }

        user.password = Encryption.createPassword(user.password);

        SessionHandling.login(user.email);

        Ebean.save(user);

        return redirect(controllers.routes.Home.home());

    }

}
