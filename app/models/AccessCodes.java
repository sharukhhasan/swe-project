package models;

import Util.Encryption;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import play.data.format.*;


@Entity
public class AccessCodes extends Model {

	@Id
	public Long id;
	public Boolean used;
	public String role;
}