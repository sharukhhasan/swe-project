package models;

<<<<<<< HEAD
import Util.Encryption;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import play.data.format.*;

=======
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
>>>>>>> sharukhDev

@Entity
public class AccessCodes extends Model {

<<<<<<< HEAD
	@Id
	public Long id;
	public Boolean used;
	public String role;

	  public static Finder<Long,AccessCodes> find = new Finder<Long,AccessCodes>(Long.class, AccessCodes.class); 
}
=======
    @Id
    public Long id;
    public Boolean used;
    public String role;

    public static Finder<Long,AccessCodes> find = new Finder<Long,AccessCodes>(Long.class, AccessCodes.class);
}
>>>>>>> sharukhDev
