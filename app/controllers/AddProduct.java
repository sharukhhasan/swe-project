package controllers;

import com.avaje.ebean.Ebean;
import models.Product;
import models.forms.ProductForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.addproduct;

/**
 * Created by Sharukh on 3/21/16.
 */
public class AddProduct extends Controller {

    public Result product()
    {
        return ok(addproduct.render(Form.form(ProductForm.class)));
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
