package hashset.OOP.obukhovaEV.main.set;

import java.util.Arrays;
import java.util.Objects;

public class SimpleHashSet<T> implements ISet<T> {
    private transient Node<T, Object>[] table;
    private final double LOAD_FACTOR;
    private final int DEF_CAPACITY;
    private static final Object VALUE = new Object();
    private int filled = 0;

    public SimpleHashSet() {
        this.LOAD_FACTOR = .75;
        this.DEF_CAPACITY = 16;
    }

    public SimpleHashSet(double LOAD_FACTOR) {
        this.LOAD_FACTOR = LOAD_FACTOR;
        this.DEF_CAPACITY = 16;
    }

    public SimpleHashSet(double LOAD_FACTOR, int DEF_CAPACITY) {
        this.LOAD_FACTOR = LOAD_FACTOR;
        this.DEF_CAPACITY = DEF_CAPACITY;
    }


    @Override
    public boolean add(T element) {
        boolean modified = false;
        /*int n = (int) (table.length * LOAD_FACTOR);
        if (table[n] != null) {
            resizeTable();
        }
         */
        if (filled >= (table.length * LOAD_FACTOR)) {
            resizeTable();
        }

        int hash = element.hashCode();
        int index = index(hash);

        Node<T, Object> newElement = new Node<>(hash, element, VALUE, null);

        if (table[index] == null) {
            table[index] = newElement;
        } else {
            Node<T, Object> oldElement = table[index];
            while (oldElement.getNext() != null) {
                if (oldElement.equals(newElement)) {
                    break;
                }
                oldElement = oldElement.getNext();
            }
            if (!oldElement.equals(newElement)) {
                oldElement.setNext(newElement);
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean remove(T element) {
        boolean modified = false;
        int hash = element.hashCode();
        int index = index(hash);

        Node<T, Object> currElement = table[index];
        T currKey = currElement.getKey();
        while (currElement != null) {
            if (hash == currElement.getHash() &&
                    Objects.equals(element, currKey)) {
                if (currElement.getNext() != null)
                    table[index] = currElement.getNext();
                else table[index] = null;
                modified = true;
                // is it really necessary?
                break;
            } else {
                currElement = currElement.getNext();
            }
        }
        return modified;
    }

    @Override
    public boolean contains(T element) {
        int hash = element.hashCode();
        int index = index(hash);

        Node<T, Object> currElement = table[index];
        while (currElement != null) {
            if (hash == currElement.getHash() &&
                    Objects.equals(element, currElement.getKey())) {
                return true;
            }
            currElement = currElement.getNext();
        }
        return false;
    }

    @Override
    public void clear() {
        table = (Node<T, Object>[]) new Node[16];
    }

    /**
     * Returns the number of unique elements in this {@link SimpleHashSet}
     *
     * @return size of {@link SimpleHashSet}
     */
    @Override
    public int size() {
        return table.length;
    }

    /**
     * Checks if the {@link SimpleHashSet} contains any element
     *
     * @return boolean
     */
    @Override
    public boolean isEmpty() {
        return table.length > 0;
    }

    private void resizeTable() {
        int n = table.length;
        Node[] buffTable = Arrays.copyOf(table, n);
        table = (Node<T, Object>[]) new Node[n + DEF_CAPACITY];
        System.arraycopy(buffTable, 0, table, 0, n);
    }

    private int index(int hashCode) {
        return hashCode & (table.length - 1);
    }
}
