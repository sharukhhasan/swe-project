package controllers;

import com.avaje.ebean.Ebean;
import models.User;
import models.forms.UserForm;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by Sharukh on 4/25/16.
 */
public class Admin extends Controller {

    public Result editUsersPage()
    {
        List<User> userList = Ebean.find(User.class)
                .select("*")
                .findList();

        return ok(views.html.edituserpage.render(userList));
    }

    public Result editUser(Long user_id)
    {
        DynamicForm requestData = DynamicForm.form().bindFromRequest();
        User user = Ebean.find(User.class)
                .select("*")
                .where()
                .eq("id", user_id)
                .findUnique();

        user.email = requestData.get("email");
        user.firstName = requestData.get("firstName");
        user.lastName = requestData.get("lastName");
        user.role = requestData.get("role");

        Ebean.save(user);

        return redirect(routes.Admin.editUsersPage());
    }

    public Result getEditingUser(Long user_id)
    {
        User user = Ebean.find(User.class)
                .select("*")
                .where()
                .eq("id", user_id)
                .findUnique();

        return ok(views.html.edituser.render(Form.form(UserForm.class), user));
    }

    public Result getPendingRoles()
    {
        List<User> pendingList = Ebean.find(User.class)
                .select("*")
                .where()
                .eq("confirm_role", false)
                .findList();

        return ok(views.html.pendingRoles.render(pendingList));
    }

    public Result confirmRole(Long user_id)
    {
        User user = Ebean.find(User.class)
                .select("*")
                .where()
                .eq("id", user_id)
                .findUnique();

        user.confirm_role = true;
        Ebean.save(user);

        return redirect(routes.Admin.getPendingRoles());
    }


}
