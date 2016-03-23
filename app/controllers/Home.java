package controllers;

import Util.SessionHandling;
import db.DatabaseHelper;
import models.Product;
import models.forms.ProductForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Home extends Controller {

    static Form<Product> productForm = Form.form(Product.class);

    public Result home() {
   	 if (SessionHandling.isLoggedIn()) {
         return ok(views.html.home.render(ProductForm.all(), productForm));
        }
        else {
            return redirect(controllers.routes.Login.login());
        }
   }

    public Result products()
    {
        return ok(views.html.home.render(DatabaseHelper.getProductsFromDB(), productForm));
    }
    

}