package controllers;

import java.util.*;
import play.data.Form;
import play.data.validation.Constraints;

import play.mvc.*;
import models.*;

import Util.SessionHandling;
import views.html.*;

import com.avaje.ebean.*;

public class Login extends Controller {
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String password;

    public Result login() {
        return ok(login.render(Form.form(User.class)));
    }

    public Result validateLogin() {
	    Form<User> form = Form.form(User.class).bindFromRequest();
	    User user = form.get();
	    List<User> userResult = Ebean.find(User.class)
	        .where().like("email", user.email)
	        .where().like("password", user.password)
	        .findList();
	    if (userResult.size() > 0) {
	    	String userLoggedIn = userResult.get(0).email;
	    	SessionHandling.login(userLoggedIn);
	        return redirect(controllers.routes.Home.home());
	    }
	    else {
	        return redirect(controllers.routes.Error.error("Not a valid user:" +user.email + user.password));
	    }


	}

    public Result authenticate()
    {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();

        Form<Registration> registerForm = Form.form(Registration.class);

        if(loginForm.hasErrors())
        {
            return badRequest(index.render(registerForm, loginForm));
        }
        else
        {
            session("email", loginForm.get().email);
            return redirect(controllers.routes.Home.home());
        }
    }
    

}