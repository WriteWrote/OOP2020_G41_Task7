package hashset.OOP.obukhovaEV.main.set;

import java.util.Set;

public class SimpleHashSet<T> implements ISet<T> {
    private transient Node<Integer, T>[] table;

    @Override
    public boolean add(T element) {
        // check if table needs resizing
        // private void of copying and resizing
        // check if element exists in the table
        //adding element (use hash for god's sake!)
        // if table was modified, return true
        return false;
    }

    @Override
    public boolean remove(T element) {
        //check if element exists in the table
        //delete element
        // if table was modified, return true
        return false;
    }

    @Override
    public boolean contains(T element) {
        // check hash of element
        // find correct bucket
        // check with equals every element in the bucket
        // if any element isEqual, return true
        return false;
    }

    @Override
    public void clear() {
//just clear the table
    }

    @Override
    public int size() {
        //return size of the table
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // if table.size>0 return true
        return false;
    }
}
