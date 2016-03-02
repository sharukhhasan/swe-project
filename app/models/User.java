package models;

import java.util.*;
import play.db.jpa.JPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
@Table(name = "SiteUser")
public class User {

        @Id
        @GeneratedValue
        public Long id;

        public String email;
        public String firstName;
        public String lastName;
        public String gender;
        public String password;
       
        public User(String firstName) {
                this.firstName = firstName;
        }

        public User() {}

        public User(String email, String firstName, String gender) {
                this.email = email;
                this.firstName = firstName;
                this.gender = gender;
        }
}
