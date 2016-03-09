package Util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Sharukh on 3/1/16.
 */
public class Encryption {

    public static String createPassword(String clearString)
    {
        if(clearString == null)
        {
            return Constants.PASSWORD_NULL;
        }
        return BCrypt.hashpw(clearString, BCrypt.gensalt());
    }

    public static boolean checkPassword(String userInput, String encryptedPassword)
    {
        if(userInput == null)
        {
            return false;
        }

        if(encryptedPassword == null)
        {
            return false;
        }

        return BCrypt.checkpw(userInput, encryptedPassword);
    }
}
