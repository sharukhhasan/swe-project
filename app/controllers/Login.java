package controllers;

import java.util.*;

import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.*;
import models.*;
import models.forms.*;
import Util.Encryption;
import Util.SessionHandling;
import views.html.*;

import com.avaje.ebean.*;

public class Login extends Controller {

    public Result login() {
        return ok(login.render(Form.form(models.forms.LoginForm.class)));
    }

    public Result validateLogin() {
    	Form<models.forms.LoginForm> form = Form.form(models.forms.LoginForm.class).bindFromRequest();
    	models.forms.LoginForm user = form.get();
	    List<User> userResult = Ebean.find(User.class)
	        .where().like("email", user.email)
	        .findList();
	    if (userResult.size() > 0) {
	    	if (Encryption.checkPassword(user.password, userResult.get(0).password)) {
		    	String userLoggedIn = userResult.get(0).email;
		    	SessionHandling.login(userLoggedIn);
		        return redirect(controllers.routes.Home.home());
	    	}
	    	else {
	    		return redirect(controllers.routes.Error.error("Incorrect account or password"));
	    	}
	    }
	    else {
	        return redirect(controllers.routes.Error.error("Not a valid user:" + user.email));
	    }


	}

    

}
