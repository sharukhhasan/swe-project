package controllers;

import Util.Constants;
import com.avaje.ebean.Ebean;
import models.Product;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Sharukh on 3/22/16.
 */
public class ProductList extends Controller {
    static Form<Product> productForm = Form.form(Product.class);

    public Result products()
    {
        return ok(views.html.viewproducts.render(Product.all(), Constants.categories));
    }

    public Result singleproduct(Long id)
    {
        return ok(views.html.singleproduct.render((Product.single(id))));
    }

    public Result searchProducts()
    {
        DynamicForm searchForm = DynamicForm.form().bindFromRequest();
        String searchBy = searchForm.get("search-term");
        List<Product> queryList = Ebean.find(Product.class)
                .select("*")
                .where()
                .contains("product_name", searchBy)
                .findList();

        return ok(views.html.viewproducts.render(queryList, Constants.categories));
    }

    public Result searchByCategory()
    {
        DynamicForm searchForm = DynamicForm.form().bindFromRequest();
        String searchBy = searchForm.get("categoryDropDown");
        String searchItem = searchForm.get("category-item");
        System.out.println(searchBy);
        System.out.println(searchItem);
        List<Product> queryList = Ebean.find(Product.class)
                .select("*")
                .where()
                .contains("product_category", searchBy)
                .findList();

        return ok(views.html.viewproducts.render(queryList, Constants.categories));
    }
}
