package controllers;

import play.*;

import java.util.*;

import play.mvc.*;
import models.User;
import models.EmailActivation;

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

	private String nextToken() {
	  	SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(16);
	}


	public Result register() {
        return ok(register.render(Form.form(User.class)));
    }

    public Result addUser() {
    	Form<User> form = Form.form(User.class).bindFromRequest();
    	User user = form.get();

    	List<User> userResult = Ebean.find(User.class)
            .where().like("email", user.email)
            .findList();
        if(userResult.size() > 0) {
        	return redirect(controllers.routes.Error.error("User exists for: " + user.email));
        }

        EmailActivation token = new EmailActivation();

        token.email = user.email;
        token.token = nextToken();

        Ebean.save(token);
        Ebean.save(user);

        String messageBody = "default";

        if (play.Play.isProd()) {
        	messageBody = "Hi " + user.firstName + "please activate your account at: swe-project.herokuapp.com/activateaccount/" + token.token;
        } else {
        	messageBody = "Hi " + user.firstName + "please activate your account at: localhost:9000/activateaccount/" + token.token;
        }
        
        String messageTitle = "Please activate your account";
        try {
        	SendEmail.SendMail(user.email, messageTitle, messageBody);
        } catch(Exception e) {
        	return redirect(controllers.routes.Error.error("Could not send email: " + user.email + " error" + e));
        }
        
    	
    	return redirect(controllers.routes.Application.index());
    }

}