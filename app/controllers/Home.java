package controllers;

import Util.SessionHandling;
import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;

public class Home extends Controller {

    static Form<Product> productForm = Form.form(Product.class);

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