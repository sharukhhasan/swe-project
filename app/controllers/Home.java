package controllers;

import play.*;

import java.util.*;
import play.data.Form;

import play.mvc.*;
import models.*;

import Util.SessionHandling;
import views.html.*;

public class Home extends Controller {

    public Result home() {
   	 if (SessionHandling.isLoggedIn()) {
            String user = SessionHandling.getUser();
            return ok(home.render(user));
        }
        else {
            return redirect(controllers.routes.Login.login());
        }
   }
    

}