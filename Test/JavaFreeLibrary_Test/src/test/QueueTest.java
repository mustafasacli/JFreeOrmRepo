package test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import static java.lang.System.out;

/**
 *
 * @author Krkt
 */
public class QueueTest {

    public static void main(String[] args) {
        Queue queueA = new LinkedList();

        queueA.add("element 0");
        queueA.add("element 1");
        queueA.add("element 2");

        //access via Iterator
        Iterator iterator = queueA.iterator();
        while (iterator.hasNext()) {
            String element = (String) iterator.next();
            out.println(element);
        }

        //access via new for-loop
        for (Object object : queueA) {
            String element = (String) object;
            out.println(element);
        }
    }
}
