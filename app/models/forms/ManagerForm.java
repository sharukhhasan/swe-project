package models.forms;

import play.data.validation.Constraints;

/**
 * Created by Sharukh on 3/17/16.
 */
public class ManagerForm {
    @Constraints.Required
    public int managerid;

}
