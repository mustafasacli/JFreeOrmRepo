package test;

import static java.lang.System.out;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 *
 * @author Krkt
 */
public class LinkedHashMapTest {

    public static void main(String[] args) throws Exception {
        LinkedHashMap<String, Object> h = new LinkedHashMap<>();
        h.put("first_name", "hasan");
        h.put("last_name", "caglar");
        h.put("salary", 2800);
        Set<String> s = h.keySet();
        s.stream().forEach((s1) -> {
            out.println(String.format("Key: %s - Value: %s", s1, h.get(s1)));
        });
        /*
         for (String s1 : s) {
         out.println(String.format("Key: %s - Value: %s", s1, h.get(s1)));
         }
         */
    }
}
