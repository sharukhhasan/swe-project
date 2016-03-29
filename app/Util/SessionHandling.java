package Util;

import play.*;
import play.api.*;
import play.mvc.*;
import play.mvc.Http.*;
import play.mvc.Http.Session;

import views.html.*;

public class SessionHandling extends Controller {

	public static Boolean isLoggedIn() {
	String user = session("connected");
	return (user != null);
	}

	public static String getUser() {
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

