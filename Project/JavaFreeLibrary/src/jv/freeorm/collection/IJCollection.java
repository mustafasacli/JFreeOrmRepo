package jv.freeorm.collection;

import java.util.Iterator;

/**
 *
 * @author Mustafa SACLI
 * @param <T>
 */
public interface IJCollection<T> {
    
    boolean contains(T t);
    
    T get(int index);
    
    int firstIndexOf(T t);
    
    boolean add(T t);
    
    boolean remove(T t);
    
    boolean removeAt(int index);
    
    int size();
    
    Iterator asIterator();
    
    boolean isEmpty();
    
    void clear();
            
    <T> T[] toArray(T[] a);
    
    boolean isValidIndex(int index);
}
