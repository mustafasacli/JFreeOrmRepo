package test;

import jv.freeorm.base.DriverType;
import jv.freeorm.base.Hashtable;
import static java.lang.System.out;

/**
 *
 * @author Krkt
 */
public class PersonUpdateTest {

    public static void main(String[] args) throws Exception {

        Hashtable h = new Hashtable();
        h.set("first_name", "hasan updated");
        h.set("last_name", "caglar updated");
        h.set("salary", 6800);

        try (PersonDL p = new PersonDL(DriverType.MySQL, "jdbc:mysql://localhost:3306/pydb", "root", "")) {
            int result = p.Update("person", "person_id", 5, h);
            out.println(result);
        }
    }
}
