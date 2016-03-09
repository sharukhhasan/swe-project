package controllers;

import play.*;
import java.util.*;
import play.mvc.*;
import models.*;
import Util.SendEmail;
import views.html.*;
import com.avaje.ebean.*;
import java.security.SecureRandom;
import java.math.BigInteger;
import play.data.Form;

public class PasswordReset extends Controller {

private String nextToken() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(16);
    }

    public Result Lander() {
         return ok(pwdResetLander.render(Form.form(User.class)));
    }

    public Result PasswordResetRedirect(String token) {
        PasswordResets pwdRecord = Ebean.find(PasswordResets.class)
            .where().like("token", token)
            .findUnique();
        if(pwdRecord == null || pwdRecord.used == true) {
        	return redirect(controllers.routes.Error.error("Invalid link for password reset token: " + token));
        } else {
        	User user = Ebean.find(User.class)
            .where().like("email", pwdRecord.email)
            .findUnique();
            pwdRecord.used = true;
            Ebean.save(pwdRecord);
            return ok(pwdreset.render(Form.form(User.class), user));
        }
    }
    
    public Result PasswordReset(String email) {
        Form<User> form = Form.form(User.class).bindFromRequest();
        User userForm = form.get();
        User user = Ebean.find(User.class)
            .where().like("email", email)
            .findUnique();
        if(user == null) {
            return redirect(controllers.routes.Error.error("could not find user for email from form " + userForm.email + " Old user: "));
        }
        user.password = userForm.password;
        Ebean.save(user);
        return ok(genericLander.render("Your password has been reset", "Password reset for user " + user.email));
    }

    public Result SendPasswordEmail() {
        Form<User> form = Form.form(User.class).bindFromRequest();
        User formUser = form.get();
        User user = Ebean.find(User.class)
            .where().like("email", formUser.email)
            .findUnique();
        PasswordResets token = new PasswordResets();
        token.token = nextToken();
        token.email = user.email;

        Ebean.save(token);

        String messageBody = "default";

        if (play.Play.isProd()) {
            messageBody = "Hi " + user.firstName + " please reset your password at: swe-project.herokuapp.com/passwordreset/" + token.token;
        } else {
            messageBody = "Hi " + user.firstName + " please reset your password at: localhost:9000/passwordreset/" + token.token;
        }

        String messageTitle = "SWEPROJECT Password reset";

        try {
            SendEmail.SendMail(user.email, messageTitle, messageBody);
        } catch(Exception e) {
            return redirect(controllers.routes.Error.error("Could not send email: " + user.email + " error: " + e));
        }
        
        return ok(genericLander.render("Your password email has been sent", "Password reset email has been sent for user " + user.email));
        
    }

}