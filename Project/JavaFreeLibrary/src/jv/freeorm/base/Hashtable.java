package jv.freeorm.base;

import java.util.HashMap;
import java.util.Stack;
import jv.freeorm.util.StringUtil;

/**
 *
 * @author Mustafa SACLI
 */
public final class Hashtable {

    private volatile HashMap h = null;
    private volatile Stack<String> _keys = null;

    public Hashtable() {
        h = new HashMap();
        _keys = new Stack<>();
    }

    /// <summary>
    /// Returns true if Hasmap is empty, else  returns false.
    /// </summary>
    /// <returns>returns a bool Object</returns>
    public boolean isEmpty() {
        boolean rt;
        rt = _keys.isEmpty();
        return rt;
    }

    /**
     *
     * @return
     */
    public int count() {
        int _count;
        _count = _keys.size();
        return _count;
    }

    /// <summary>
    /// Gets keys as a String array.
    /// </summary>
    /// <returns>Returns a String array.</returns>
    public String[] keys() {
        String[] arr = new String[_keys.size()];
        _keys.toArray(arr);
        return arr;
    }

    public Object get(String key) {
        Object val;

        //if (_keys.indexOf(key) > -1) {
        val = h.get(key);
        //}

        return val;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {

        if (StringUtil.isNullOrWhiteSpace(key) == false) {
            //  throw new Exception("Key can not be null, empty or white space.");

            h.put(key, value);

            if (_keys.indexOf(key) == -1) {
                _keys.addElement(key);
            }
        }

    }

    /**
     *
     * @param key
     */
    public void remove(String key) {
        try {
            h.remove(key);
            _keys.remove(key);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean contains(String key) {
        boolean rt;
        rt = h.containsKey(key);
        return rt;
    }
}
