package controllers;

import com.avaje.ebean.Ebean;
import models.User;
import models.Product;
import models.forms.ProductForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.addproduct;
import Util.SessionHandling;


/**
 * Created by Sharukh on 3/21/16.
 */
public class AddProduct extends Controller {


    public Result product()
    {
        String userEmail = SessionHandling.getUserEmail();

       User user = Ebean.find(User.class)
               .where().like("email", userEmail)
               .findUnique();
       if(user == null) {
            return redirect(controllers.routes.Error.error("User not found, please log in and try again"));
       }
       if(user.role.equals("manager")  || user.role.equals("admin")) {
            return ok(addproduct.render(Form.form(ProductForm.class)));
       } else {
            return redirect(controllers.routes.Error.error("User " + user.email + " does not have ability to add product!"));
       }
       
    }

    public Result addProduct()
    {
        Form<ProductForm> form = Form.form(ProductForm.class).bindFromRequest();
        ProductForm productForm = form.get();
        Product product = productForm.formToProduct(productForm);

        Ebean.save(product);

        return redirect(routes.Home.home());
    }
}
