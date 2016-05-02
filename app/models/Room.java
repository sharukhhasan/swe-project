package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Sharukh on 5/1/16.
 */
@Entity
@Table(name = "rooms")
public class Room extends Model {

    @Id
    public Long id;

    public Boolean isOccupied;

}
