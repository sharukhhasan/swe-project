package controllers;

import play.*;

import java.util.*;

import play.mvc.*;
import models.User;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.*;

import Util.SessionHandling;
import views.html.*;


public class Application extends Controller {

    public Result sessionRedirect() {
        if (SessionHandling.isLoggedIn()) {
            String user = SessionHandling.getUser();
            return redirect(controllers.routes.Home.home());
        }
        else {
            return redirect(controllers.routes.Login.login());
        }
    }


    public Result index() {
        return ok(index.render());
    }
    
    
    public Result logout() {
    	SessionHandling.logout();
    	return redirect(controllers.routes.Login.login());
    }
   
    

}
