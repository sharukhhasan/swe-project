package controllers;

import play.*;

import java.util.*;

import play.mvc.*;
import models.User;

import com.fasterxml.jackson.databind.JsonNode;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import Util.SessionHandling;
import static play.libs.Json.toJson;
import views.html.*;

public class Application extends Controller {

    public Result sessionRedirect() {
        if (SessionHandling.isLoggedIn()) {
            String user = SessionHandling.getUser();
            return redirect(controllers.routes.Application.home());
        }
        else {
            return redirect(controllers.routes.Application.login());
        }
    }

    public Result register() {
        return ok(register.render(Form.form(User.class)));
    }

    public Result index() {
        return ok(index.render());
    }

    public Result login() {
        return ok(login.render(Form.form(User.class)));
    }
    
    public Result home() {
   	 if (SessionHandling.isLoggedIn()) {
            String user = SessionHandling.getUser();
            return ok(home.render(user));
        }
        else {
            return redirect(controllers.routes.Application.login());
        }
   }

    public Result error(String errorMsg) {
        return ok(error.render(errorMsg));
    }
    
    public Result logout() {
    	SessionHandling.logout();
    	return redirect(controllers.routes.Application.login());
    }

    @Transactional(readOnly = true)
    public Result validateLogin() {
        Form<User> form = Form.form(User.class).bindFromRequest();
        User user = form.get();
        List<User> userResult = JPA.em().createQuery(
                "SELECT u FROM User u WHERE u.email = :username AND u.password = :password")
            .setParameter("username", user.email)
            .setParameter("password", user.password)
            .getResultList();
        //User userResult = JPA.em().find(User.class, password)
        if (userResult.size() > 0) {
        	String userLoggedIn = userResult.get(0).email;
        	SessionHandling.login(userLoggedIn);
            return redirect(controllers.routes.Application.home());
        }
        else {
            return redirect(controllers.routes.Application.error("Not a valid user:" +user.email + user.password));
        }


    }

    @Transactional
    public Result addUser() {
    	Form<User> form = Form.form(User.class).bindFromRequest();
    	User user = form.get();
    	JPA.em().persist(user);
    	return redirect(controllers.routes.Application.index());
    }
    
    @Transactional(readOnly = true)
    public Result listUser() {
        CriteriaBuilder cb = JPA.em().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(root);
        TypedQuery<User> allQuery = JPA.em().createQuery(all);
        JsonNode jsonNodes = toJson(allQuery.getResultList());
        return ok(jsonNodes);
    }
    

}
