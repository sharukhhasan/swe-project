package controllers;

import play.*;

import java.util.*;

import Util.Encryption;

import play.mvc.*;
import models.User;
import models.EmailActivation;
import models.forms.UserForm;
import play.data.validation.Constraints;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import com.avaje.ebean.*;
import Util.SessionHandling;
import Util.SendEmail;
import views.html.*;
import java.security.SecureRandom;
import java.math.BigInteger;
import com.fasterxml.jackson.databind.JsonNode;
//import org.junit.*;
import play.mvc.*;
//import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

//import static play.test.Helpers.*;
//import static org.junit.Assert.*;

public class Test extends Controller {

	public Result test()
	    {
	    	Boolean userTest = testUser();
	        return ok(test.render(userTest));
	    }

	public Boolean testUser() {
		List<User> userResult = Ebean.find(User.class)
            .where().like("email", "test@test1234.com")
            .findList();
        if(userResult.size() != 0) {
        	User existingUser = userResult.get(0);
        	Ebean.delete(existingUser);
        }
        User user = new User();
    	user.email = "test@test1234.com";
    	user.firstName = "test";
    	user.lastName = "test";
    	user.gender = "male";
    	user.role = "user";
    	user.password = "1234";
    	user.activated = false;
    	Ebean.save(user);
    	User dbUser = Ebean.find(User.class)
    		.where().like("email", "test@test1234.com")
    		.findUnique();
    	System.out.println("User in db: " + dbUser.email);
    	if (!user.email.equals(dbUser.email)) {
    		return false;
    	}
    	Ebean.delete(dbUser);
		List<User> deletedUser = Ebean.find(User.class)
			.where().like("email", "test@test1234.com")
			.findList();
		System.out.println("Return query size: " + deletedUser.size());
		return deletedUser.size() == 0;
	}

}