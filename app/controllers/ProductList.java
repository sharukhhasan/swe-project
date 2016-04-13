package controllers;

import Util.Constants;
import com.avaje.ebean.Ebean;
import models.Product;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.*;

import java.util.List;

/**
 * Created by Sharukh on 3/22/16.
 */
public class ProductList extends Controller {
    static Form<Product> productForm = Form.form(Product.class);

    public Result products()
    {
        Map<String, List<String>> categories = getCategories();
        return ok(views.html.viewproducts.render(Product.all(), categories));//Constants.categories));
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
                .disjunction()
                .icontains("product_name", "%" + searchBy + "%")
                .icontains("product_manufacturer", "%" + searchBy + "%")
                .icontains("product_description", "%" + searchBy + "%")
                .endJunction()
                .findList();
        Map<String, List<String>> categories = getCategories();
        return ok(views.html.viewproducts.render(queryList, categories));
    }

    public Map<String, List<String>> getCategories() {
        Map<String, List<String>> categoryMap = new HashMap<>();
        List<Product> products = Product.all();
        List<String> categories = new ArrayList();
        for(Product p : products) {
            if(!categories.contains(p.productCategory)){
                categories.add(p.productCategory);
            }
            
        }
        categoryMap.put("Items", categories);
        return categoryMap;
    }

    public Result searchByCategory(String element)
    {
        /*
        DynamicForm searchForm = DynamicForm.form().bindFromRequest();
        String searchBy = searchForm.get("categoryDropDown");
        String searchItem = searchForm.get("category-item");
        System.out.println(searchBy);
        System.out.println(searchItem);*/
        List<Product> queryList = Ebean.find(Product.class)
                .select("*")
                .where()
                .icontains("product_category", element)
                .findList();
        Map<String, List<String>> categories = getCategories();
        return ok(views.html.viewproducts.render(queryList, categories));
    }
}
