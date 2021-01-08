package hashset.OOP.obukhovaEV.main;

import java.util.*;

public class SimpleHashSet<T> {
    private static final Object VALUE = new Object();
    private transient HashMap<T, Object> map;

    public SimpleHashSet() {
        this.map = new HashMap<>();
    }

    public SimpleHashSet(int startCapacity) {
        this.map = new HashMap<>(startCapacity);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contains(T t) {
        return map.containsKey(t);
    }

    public Iterator<T> iterator() {
        return null;
    }

    public Object[] toArray() {
        List<T> list = new LinkedList<>(map.keySet());
        return list.toArray();
    }

    public boolean add(T t) {
        return map.put(t, VALUE) == null;
    }

    public boolean remove(T t) {
        return map.remove(t) == VALUE;
    }

    public boolean addAll(Collection<? extends T> c) {
        boolean contains = false;
        for (T el :
                c) {
            if (map.containsKey(el)) {
                contains = true;
                break;
            }
        }
        if (contains) {
            Map<T, Object> mRes = new HashMap<>();
            for (T el :
                    c) {
                mRes.put(el, VALUE);
            }
            map.putAll(mRes);
        }
        return contains;
    }

    public boolean removeAll(Collection<? extends T> c) {
        boolean changed = false;
        for (T el :
                c) {
            if (map.containsKey(el)) {
                map.remove(el);
                changed = true;
            }
        }
        return changed;
    }

    public void clear() {
        map.clear();
    }
}
