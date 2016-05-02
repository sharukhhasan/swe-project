package controllers;

import Util.SessionHandling;
import models.Guest;
import models.Room;
import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.home;

import java.util.List;

public class Home extends Controller {

    public Result home() {
   	 if (SessionHandling.isLoggedIn()) {
         User user = SessionHandling.getUser();
         List<Room> listRooms = RoomController.getRooms();
         List<Guest> listGuests = GuestController.getGuests();

         return ok(home.render(user, listRooms, listGuests));
         //return ok(home.render(user.firstName + " " + user.lastName));
        }
        else {
            return redirect(controllers.routes.Login.login());
        }
   }
    

}