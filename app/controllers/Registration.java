package controllers;

import play.*;

import java.util.*;

import Util.Encryption;
import play.mvc.*;
import models.Cart;
import models.User;
import models.EmailActivation;
import models.AccessCodes;
import models.forms.UserForm;
import models.Address;
import play.data.validation.Constraints;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.*;

import Util.SessionHandling;
import Util.SendEmail;
import views.html.*;

import java.security.SecureRandom;
import java.math.BigInteger;


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
						return ok(genericLander.render("SWE-Project - Error", "You already have this role!"));
					}
					u.role = role;
					u.confirm_role = false;
					Ebean.save(u);
					return ok(genericLander.render("SWE-Project - Role Requested", "Your request to be a " + role + " has been sent!"));
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
        Address oldAddress = Ebean.find(Address.class)
                .select("*")
                .where().eq("user_id", user.id)
                .findUnique();
        if (oldAddress != null) {
            Ebean.delete(oldAddress);
        }
        Ebean.save(address);
        return ok(genericLander.render("SWE-Project - Contact Information Updated", "Your contact information has been updated!"));
	}

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

        EmailActivation token = new EmailActivation();

        token.email = user.email;
        token.token = nextToken();

        String messageBody = "default";

        if(play.Play.isProd())
        {
        	messageBody = "Hi " + user.firstName + ",\n\nThank you for registering for SWE-Project. Please activate your account at the following link:\n\nswe-project.herokuapp.com/activateaccount/" + token.token + "\n\nThe SWE-Project Team";
        }
        else
        {
        	messageBody = "Hi " + user.firstName + ",\n\nThank you for registering for SWE-Project. Please activate your account at the following link:\n\nlocalhost:9000/activateaccount/" + token.token + "\n\nThe SWE-Project Team";
        }
        
        String messageTitle = "Please activate your account";
        try {
        	SendEmail.SendMail(user.email, messageTitle, messageBody);
        } catch(Exception e) {
        	return redirect(controllers.routes.Error.error("Could not send email: " + user.email + " error" + e));
        }

        SessionHandling.login(user.email);
        
        if(userForm.role.equals("manager") || userForm.role.equals("admin"))
        {
            if(userForm.managerid == null) {
                return redirect(controllers.routes.Error.error("Must validate privileged access with code. managerid: " + userForm.managerid));
            }

            Long managerId = null;

            try {
                managerId = Long.valueOf(userForm.managerid).longValue();
            } catch (NumberFormatException e) {
                return redirect(controllers.routes.Error.error("Not a valid manager code"));
            }

            AccessCodes code = AccessCodes.find.byId(managerId);
            
            if(code == null) {
                return redirect(controllers.routes.Error.error("Invalid manager code"));
            }

            code.used = true;
            Ebean.save(code);
        }

        Ebean.save(token);
        Ebean.save(user);

    	return redirect(controllers.routes.Home.home());

    }

}