package Util;

import play.*;
import models.*;
import com.avaje.ebean.*;
import java.util.List;
import java.util.Arrays;


public class Global extends GlobalSettings {

    public void onStart(Application app) {
        Logger.info("Application has started");

        Logger.info("Adding access codes to db");

        List<Long> adminCodes = Arrays.asList(1234L, 4321L, 6666L, 6969L, 42069L);
        List<Long> managerCodes = Arrays.asList(1111L, 2222L, 3333L, 4444L, 5555L);

        for (Long l : adminCodes) {
	        AccessCodes code = AccessCodes.find.byId(l);
	        if (code != null) {
	        	System.out.println(l + " exists in db");
	        } else {
		        System.out.println(l + "is null");
		    	code = new AccessCodes();
		    	code.id = l;
		    	code.used = false;
		    	code.role = "admin";
		    	Ebean.save(code);
		    	System.out.println("Saved " + l);
	    	}
	    }

        for (Long l : managerCodes) {
            AccessCodes code = AccessCodes.find.byId(l);
            if (code != null) {
                System.out.println(l + "exists in db");
            } else {
                System.out.println(l + " is null");
                code = new AccessCodes();
                code.id = l;
                code.used = false;
                code.role = "manager";
                Ebean.save(code);
                System.out.println("Saved " + l);
            }
        }

    }


}