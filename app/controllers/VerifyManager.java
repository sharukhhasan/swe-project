package controllers;

import models.User;
import models.forms.UserForm;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;

/**
 * Created by Sharukh on 3/17/16.
 */
public class VerifyManager extends Controller {
    public String testID = "abcdefg";

    public Result managerId()
    {
        Form<UserForm> form = Form.form(UserForm.class).bindFromRequest();
        UserForm userForm = form.get();
        User user = userForm.formToUser(userForm);

        String m_id = userForm.managerid;

        if(m_id.equals(testID))
        {
            user.managerid = userForm.managerid;
            return redirect(routes.Home.home());
        }

        return redirect(controllers.routes.Application.index());
    }
}
