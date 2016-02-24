package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import controllers.DBConnect;

public class Application extends Controller {

    public Result index() {
    	DBConnect dbTester = new DBConnect();
    	try {
			dbTester.startConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ok(index.render("Your new application is ready."));
    }
    

}
