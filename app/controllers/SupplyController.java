package controllers;

import com.avaje.ebean.Ebean;
import models.Supply;
import models.forms.SupplyForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Sharukh on 5/1/16.
 */
public class SupplyController extends Controller{

    public Result supply()
    {
        return ok(views.html.addsupply.render(Form.form(SupplyForm.class)));
    }

    public Result addSupply()
    {
        Form<SupplyForm> form = Form.form(SupplyForm.class).bindFromRequest();
        SupplyForm supplyForm = form.get();
        Supply supply = supplyForm.formToSupply(supplyForm);

        Ebean.save(supply);

        return redirect(routes.Home.home());
    }
}
