package controllers;

import models.Supply;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Sharukh on 5/1/16.
 */
public class SupplyList extends Controller{

    public Result supplies()
    {
        List<Supply> products = Supply.all();

        return ok(views.html.viewproducts.render(products));
    }
}
