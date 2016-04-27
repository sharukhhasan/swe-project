package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import com.avaje.ebean.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "address")
public class Address extends Model{

    @Id
    public Long id;
    
    public String address;

    public String city;

    public String state;

    public int zip;
    
    @OneToOne
    public User user;


}
