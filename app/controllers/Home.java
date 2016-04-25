package controllers;

import Util.SessionHandling;
import models.Product;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;

public class Home extends Controller {

    static Form<Product> productForm = Form.form(Product.class);

    public Result home() {
   	 if (SessionHandling.isLoggedIn()) {
         User user = SessionHandling.getUser();
         
         return ok(home.render(user.firstName + " " + user.lastName));
        }
        else {
            return redirect(controllers.routes.Login.login());
        }
   }
    

}