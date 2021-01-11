package hashset.OOP.obukhovaEV.main;

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

    public int getHash() {
        return this.hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?, ?> node = (Node<?, ?>) o;
        return hash == node.hash &&
                Objects.equals(key, node.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }
}
