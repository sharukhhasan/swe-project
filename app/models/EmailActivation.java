package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;

@Entity
public class EmailActivation extends Model {

        @Id
        @GeneratedValue
        public Long id;
        public String email;
        public String token;
        public Boolean used = false;


}
