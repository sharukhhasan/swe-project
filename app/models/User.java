package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;

@Entity
@Table(name = "users")
public class User extends Model {


        @Id
        @GeneratedValue
        public Long id;

        public String email;
        public String firstName;
        public String lastName;
        public String gender;
        public String password;
        public String role;
        public Boolean activated = false;

}
