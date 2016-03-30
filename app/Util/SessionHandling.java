package Util;

import play.*;
import play.api.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.mvc.Http.Session;
import com.avaje.ebean.*;
import models.User;

import views.html.*;

public class SessionHandling extends Controller {

	public static Boolean isLoggedIn() {
	String user = session("connected");
	return (user != null);
	}
	
	public static User getUser() {
		String email = getUserEmail();
		User user = Ebean.find(User.class)
            .where().like("email", email)
            .findUnique();
        return user;
	}

	public static String getUserEmail() {
		String user = session("connected");
		return user;
	}

	public static void login(String username) {
		session("connected", username);
	}

	public static void logout() {
		session().remove("connected");
	}


}

