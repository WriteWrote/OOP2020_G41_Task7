package hashset.OOP.obukhovaEV.main.set;

import java.util.Objects;

public class Node<K, V> {
    private final int hash;
    private final K key;
    private V value;

    private Node<K, V> next;

    public Node(int hash, K key, V value, Node<K, V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public V setValue(V newValue) {
        V oldValue = this.value;
        this.value = newValue;
        return oldValue;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Node) {
            Node<?, ?> n = (Node<?, ?>) o;
            return Objects.equals(this.key, n.getKey()) &&
                    Objects.equals(this.value, n.getValue());
        }
        return false;
    }

    public int getHash() {
        return this.hash;
    }
}
