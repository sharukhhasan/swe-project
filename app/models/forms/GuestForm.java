package models.forms;

import models.Guest;
import play.data.validation.Constraints;

import java.util.Date;

/**
 * Created by Sharukh on 5/1/16.
 */
public class GuestForm {
    @Constraints.Required
    public String guest_first_name;
    @Constraints.Required
    public String guest_last_name;
    @Constraints.Required
    public String guest_gender;
    @Constraints.Required
    public Date guest_birthdate;
    @Constraints.Required
    public String guest_platenum;
    @Constraints.Required
    public Date guest_checkin;
    @Constraints.Required
    public Long room_number;
    @Constraints.Required
    public int num_nights;

    public Guest formToGuest(GuestForm guestForm)
    {
        Guest guest = new Guest();

        guest.guest_first_name = guestForm.guest_first_name;
        guest.guest_last_name = guestForm.guest_last_name;
        guest.guest_gender = guestForm.guest_gender;
        guest.guest_birthdate = guestForm.guest_birthdate;
        guest.guest_platenum = guestForm.guest_platenum;
        guest.guest_checkin = guestForm.guest_checkin;
        guest.room_number = guestForm.room_number;
        guest.num_nights = guestForm.num_nights;
        return guest;
    }
}
