/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.freeorm.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mustafa SACLI
 * @param <T>
 *
 * @since 1.7
 */
public class JCollection<T> implements IJCollection<T> {

    protected List<T> t_Array = null;//new ArrayList<T>();
    private int count;

    public JCollection() {
        count = 0;
        t_Array = new ArrayList<T>();
    }

    @Override
    public boolean contains(T t) {
        int indx = firstIndexOf(t);
        boolean result = indx != -1;
        return result;
    }

    @Override
    public T get(int index) {
        T t_ = null;
        boolean is_valid_index = isValidIndex(index);

        if (is_valid_index) {
            t_ = t_Array.get(index);
        }

        return t_;
    }

    @Override
    public int firstIndexOf(T t) {
        int index = -1;

        for (int i = 0; i < count; i++) {
            if (t.equals(t_Array.get(i))) {
                index = i;
                break;
            }
        }

        return index;
    }

    @Override
    public boolean add(T t) {
        boolean result = t_Array.add(t);
        return result;
    }

    @Override
    public boolean remove(T t) {
        try {
            boolean result = t_Array.remove(t);
            count -= 1;
            return result;

        } catch (Exception exc) {
            throw exc;
        }
    }

    @Override
    public boolean removeAt(int index) {
        boolean is_valid_indx = isValidIndex(index);
        if (is_valid_indx) {
            t_Array.remove(index);
            --count;
        }

        return is_valid_indx;
    }

    @Override
    public int size() {
        return t_Array.size();
    }

    @Override
    public Iterator asIterator() {
        return t_Array.iterator();
    }

    @Override
    public boolean isEmpty() {
        boolean result;
        result = count == 0;
        return result;
    }

    @Override
    public void clear() {
        count = 0;
        t_Array = new ArrayList<T>();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        //return null;
        return t_Array.toArray(a);
    }

    @Override
    public boolean isValidIndex(int index) {
        boolean result;
        result = index > -1 && index < count;
        return result;
    }

}
