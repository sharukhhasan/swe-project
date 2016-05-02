package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import Util.SessionHandling;
import views.html.*;


public class Application extends Controller {

    public Result sessionRedirect() {

        if (SessionHandling.isLoggedIn()) {
            String user = SessionHandling.getUserEmail();
            return redirect(controllers.routes.Home.home());
        }
        else {
            return redirect(controllers.routes.Login.login());
        }
    }


    public Result index() {
        return ok(index.render());
    }

    public Result genericLander(String message) {
        return ok(genericLander.render("Lander", message));
    }
    
    
    public Result logout() {
    	SessionHandling.logout();
    	return redirect(controllers.routes.Login.login());
    }
   
    

}
