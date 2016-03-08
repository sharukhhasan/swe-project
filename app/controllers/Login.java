package controllers;

import play.*;

import java.util.*;
import play.data.Form;

import play.mvc.*;
import models.*;

import Util.SessionHandling;
import views.html.*;

public class Login extends Controller {

    public Result login() {
        return ok(login.render(Form.form(User.class)));
    }
    

}