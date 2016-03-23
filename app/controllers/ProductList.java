package controllers;

import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Sharukh on 3/22/16.
 */
public class ProductList extends Controller {
    static Form<Product> productForm = Form.form(Product.class);

    public Result products()
    {
        return ok(views.html.viewproducts.render(Product.all()));
    }
}
