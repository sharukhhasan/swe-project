package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Sharukh on 5/1/16.
 */

@Entity
@Table(name = "motel_guests")
public class Guest extends Model{

    @Id
    @GeneratedValue
    public Long id;

    public String guest_first_name;

    public String guest_last_name;

    public String guest_gender;

    public String guest_platenum;

    @Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date guest_birthdate;

    @Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date guest_checkin;

    @Formats.DateTime(pattern = "yyyy-MM-dd hh:mm:ss")
    public Date guest_checkout;

    public float guest_paid;

    public Long room_number;

    public int num_nights;

}
