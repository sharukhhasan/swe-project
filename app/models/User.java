package models;

import Util.Encryption;
import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "users")
public class User extends Model{

    @Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
    public String email;

    @Constraints.Required
    public String firstName;

    @Constraints.Required
    public String lastName;

    @Constraints.Required
    public String gender;

    @Constraints.Required
    public String role;

    @Constraints.Required
    public Date birthDate;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public String encryptedPassword;

    public String confirmPassword;

    public Boolean activated = false;

    public String userToken;

    public static Model.Finder<Long, User> userQuery = new Model.Finder<Long, User>(Long.class, User.class);

    public User() {}

    public User(String email, String firstName, String lastName, String gender, String role, Date birthDate, String _password)
    {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.role = role;
        this.birthDate = birthDate;
        this.encryptedPassword = Encryption.createPassword(_password);
    }

    public static User authenticate(String email, String clearPassword)
    {
        User user = userQuery.where().eq("email", email).findUnique();

        if(user != null)
        {
            if(Encryption.checkPassword(clearPassword, user.encryptedPassword))
            {
                return user;
            }
        }
        return null;
    }
}
