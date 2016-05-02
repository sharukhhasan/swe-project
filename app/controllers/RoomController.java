package controllers;

import com.avaje.ebean.Ebean;
import models.Room;

import java.util.List;

/**
 * Created by Sharukh on 5/1/16.
 */
public class RoomController {
    public static List<Room> roomList;

    public static List<Room> getRooms()
    {
        roomList = Ebean.find(Room.class)
                .select("*")
                .findList();
        return roomList;
    }
}
