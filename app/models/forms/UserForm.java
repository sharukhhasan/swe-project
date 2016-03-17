package models.forms;

import models.User;
import play.data.validation.Constraints;

import java.util.Date;

public class UserForm {
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
    public Integer managerid;
    @Constraints.Required
    public Date birthDate;
    @Constraints.Required
    public String password;
    @Constraints.Required
    public String confirmPassword;
	  	
    public User formToUser(UserForm userForm)
    {
        User user = new User();
	  		
        user.email = userForm.email;
        user.firstName = userForm.firstName;
        user.lastName = userForm.lastName;
        user.gender = userForm.gender;
        user.role = userForm.role;
        if(user.role.equals("manager"))
        {
            user.managerid = userForm.managerid;
        }
        user.birthDate = userForm.birthDate;
        user.password = userForm.password;
        return user;
    }
}