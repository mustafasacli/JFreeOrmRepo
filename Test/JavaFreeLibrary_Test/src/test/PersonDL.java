package test;

import jv.freeorm.base.DriverType;
import jv.freeorm.logic.BaseDL;

/**
 *
 * @author Krkt
 */
public final class PersonDL extends BaseDL {

    public PersonDL() throws Exception {
        super("main");
    }

    public PersonDL(DriverType driver_type, String url, String user, String pass)
            throws Exception {

        super(driver_type, url, user, pass);
        /*
         conf = new DbConfiguration();
         conf.setUrl(url);
         conf.setUser(user);
         conf.setPassword(pass);
         dt = driver_type;
         */
    }
}
