package controllers;

import play.*;
import play.mvc.*;
import models.User;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static play.libs.Json.toJson;



import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render(Form.form(User.class)));
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
