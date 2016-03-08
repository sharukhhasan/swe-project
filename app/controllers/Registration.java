package controllers;

import play.*;

import java.util.*;

import play.mvc.*;
import models.User;

import com.fasterxml.jackson.databind.JsonNode;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import Util.SessionHandling;
import static play.libs.Json.toJson;
import views.html.*;

public class Registration extends Controller {

	    public Result register() {
        return ok(register.render(Form.form(User.class)));
    }

}