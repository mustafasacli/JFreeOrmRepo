package test;

import static java.lang.System.out;
import jv.freeorm.base.DriverType;

/**
 *
 * @author Krkt
 */
public class PersonDeleteTest {

    public static void main(String[] args) throws Exception {

        try (PersonDL p = new PersonDL(DriverType.MySQL, "jdbc:mysql://localhost:3306/pydb", "root", "")) {
            int result = p.Delete("person", "person_id", 4);
            out.println(result);
        }
    }
}
