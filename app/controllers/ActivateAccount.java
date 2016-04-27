package controllers;

import play.*;

import java.util.*;
import play.mvc.*;
import models.*;

import Util.SessionHandling;
import views.html.*;

import com.avaje.ebean.*;

public class ActivateAccount extends Controller {

    public Result ActivateAccount(String token) {
        EmailActivation emailRecord = Ebean.find(EmailActivation.class)
            .where().like("token", token)
            .findUnique();
        if(emailRecord == null || emailRecord.used == true) {
        	return redirect(controllers.routes.Error.error("Invalid link for token: " + token));
        } else {
        	User user = Ebean.find(User.class)
            .where().like("email", emailRecord.email)
            .findUnique();
            user.activated = true;
            emailRecord.used = true;
            Ebean.save(user);
            Ebean.save(emailRecord);
            return ok(activation.render("The account for " + user.firstName + " " + user.lastName + " has been activated."));
        }


    }
    

}