package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccessCodes extends Model {

    @Id
    public Long id;
    public Boolean used;
    public String role;

    public static Finder<Long,AccessCodes> find = new Finder<Long,AccessCodes>(Long.class, AccessCodes.class);
}
