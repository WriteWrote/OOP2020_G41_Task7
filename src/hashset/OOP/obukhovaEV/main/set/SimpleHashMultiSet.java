package hashset.OOP.obukhovaEV.main.set;

import java.util.Arrays;
import java.util.Objects;

public class SimpleHashMultiSet<T> implements IMultiSet<T> {
    private transient Node<T, Integer>[] table;
    private final double LOAD_FACTOR;
    private final int DEF_CAPACITY;
    private int numberOfElements = 0;
    private int filledBaskets = 0;

    public SimpleHashMultiSet(int def_capacity) {
        DEF_CAPACITY = def_capacity;
        LOAD_FACTOR = .75;
    }

    public SimpleHashMultiSet() {
        DEF_CAPACITY = 16;
        LOAD_FACTOR = .75;
    }

    public SimpleHashMultiSet(int def_capacity, double load_factor) {
        DEF_CAPACITY = def_capacity;
        LOAD_FACTOR = load_factor;
    }

    @Override
    public boolean add(T element, int occurrences) {
        return false;
    }

    @Override
    public int numberOfUniqueElements() {
        return numberOfElements;
    }

    @Override
    public int getMultiplicity(T element) {
        if (this.contains(element)) {
            int hash = element.hashCode();
            int index = index(hash);

            Node<T, Integer> currElement = table[index];
            while (currElement != null) {
                if (hash == currElement.getHash() && Objects.equals(element, currElement.getKey())) {
                    return currElement.getValue();
                }
                currElement = currElement.getNext();
            }
        }
        return 0;
    }

    /**
     * Returns a {@link ISet<T>} with all elements presenting in this particular {@link IMultiSet<T>}
     * Each element presents only once in new collection.
     *
     * @return {@link ISet<T>}
     */
    @Override
    public ISet<T> toSet() {
        SimpleHashSet<T> simpleSet = new SimpleHashSet<>();
        for (Node<T, Integer> node :
                table) {
            if (node != null) {
                simpleSet.add(node.getKey());
            }
        }
        return simpleSet;
    }

    @Override
    public boolean add(T element) {
        if (filledBaskets >= (table.length * LOAD_FACTOR)) {
            resizeTable();
        }

        int hash = element.hashCode();
        int index = index(hash);

        Node<T, Integer> newElement = new Node<>(hash, element, 1, null);

        if (table[index] == null) {
            table[index] = newElement;
            ++numberOfElements;
            ++filledBaskets;
        } else {
            Node<T, Integer> oldElement = table[index];
            while (oldElement.getNext() != null) {
                if (oldElement.equals(newElement)) {
                    oldElement.setValue(oldElement.getValue() + 1);
                }
                oldElement = oldElement.getNext();
            }
            if (!oldElement.equals(newElement)) {
                oldElement.setNext(newElement);
            } else {
                oldElement.setValue(oldElement.getValue() + 1);
            }
            ++numberOfElements;
        }
        return true;
    }

    @Override
    public boolean remove(T element) {
        int n = 0;
        boolean modified = false;
        int hash = element.hashCode();
        int index = index(hash);

        if (this.contains(element)) {
            Node<T, Integer> currElement = table[index];

            while (currElement != null) {
                if (hash == currElement.getHash() && Objects.equals(element, currElement.getKey())) {

                    if (table[index].getNext() == null) {
                        if (table[index].getValue() == 1) {
                            table[index] = null;
                            --filledBaskets;
                        } else {
                            table[index].setValue(table[index].getValue() - 1);
                        }
                    } else {
                        if (n == 0) {
                            table[index] = table[index].getNext();
                        } else {
                            Node<T, Integer> buff = table[index];
                            for (int i = 0; i < n - 1; i++) {
                                buff = buff.getNext();
                            }
                            buff.setNext(buff.getNext().getNext());
                        }
                    }
                    --numberOfElements;
                    modified = true;
                    // is it really necessary?
                    break;
                } else {
                    currElement = currElement.getNext();
                    ++n;
                }
            }
        }
        return modified;
    }

    @Override
    public boolean contains(T element) {
        int hash = element.hashCode();
        int index = index(hash);

        Node<T, Integer> currElement = table[index];
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
        table = (Node<T, Integer>[]) new Node[16];
        numberOfElements = 0;
        filledBaskets = 0;
    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null)
                size += table[i].getValue();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return filledBaskets == 0;
    }

    private void resizeTable() {
        int n = table.length;
        Node[] buffTable = Arrays.copyOf(table, n);
        table = (Node<T, Integer>[]) new Node[n + DEF_CAPACITY];
        System.arraycopy(buffTable, 0, table, 0, n);
    }

    private int index(int hashCode) {
        return hashCode & (table.length - 1);
    }
}
