package controllers;

import play.*;

import java.util.*;
import play.data.Form;

import play.mvc.*;
import models.*;

import Util.SessionHandling;
import views.html.*;

public class Error extends Controller {

    public Result error(String errorMsg) {
        return ok(error.render(errorMsg));
    }
    

}