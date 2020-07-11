package test;

import java.util.HashMap;
import java.util.Iterator;
import static java.lang.System.out;

/**
 *
 * @author Krkt
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap h = new HashMap();
        h.put("first_name", "salim");
        h.put("last_name", "gunduz");
        h.put("salary", 2400);
        Iterator<String> keys = h.keySet().iterator();
        String key;
        Object val;
        while (keys.hasNext()) {
            key = keys.next();
            val = h.get(key);
            out.println(String.format("Key: %s - Value: %s", key, val));
        }

    }
}
