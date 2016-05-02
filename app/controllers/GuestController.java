package controllers;

import com.avaje.ebean.Ebean;
import models.Guest;
import models.forms.GuestForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.addguest;

import java.util.List;

/**
 * Created by Sharukh on 5/1/16.
 */
public class GuestController extends Controller {
    public static List<Guest> guestList;

    public Result guest()
    {
        return ok(addguest.render(Form.form(GuestForm.class)));
    }

    public Result addGuest()
    {
        Form<GuestForm> form = Form.form(GuestForm.class).bindFromRequest();
        GuestForm guestForm = form.get();
        Guest guest = guestForm.formToGuest(guestForm);

        Ebean.save(guest);

        return redirect(controllers.routes.Home.home());

    }

    public void removeGuest(Long guest_id)
    {
        Ebean.delete(Guest.class, guest_id);
        Ebean.commitTransaction();
    }

    public static List<Guest> getGuests()
    {
        guestList = Ebean.find(Guest.class)
                .select("*")
                .findList();
        return guestList;
    }
}
