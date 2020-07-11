package test;

import static java.lang.System.out;
import jv.freeorm.base.DriverType;
import jv.freeorm.base.Hashtable;

/**
 *
 * @author Krkt
 */
public class PersonAddTest {

    public static void main(String[] args) throws Exception {

        Hashtable h = new Hashtable();
        h.set("first_name", "hasan");
        h.set("last_name", "caglar");
        h.set("salary", 2800);

        try (PersonDL p = new PersonDL(DriverType.MySQL, "jdbc:mysql://localhost:3306/pydb", "root", "")) {
            int result = p.Insert("person", h);
            out.println(result);
        }
    }
}
