package hashset.OOP.obukhovaEV.main.set;

public interface ISet<T> {
    boolean add(T element);

    boolean remove(T element);

    boolean contains(T element);

    void clear();

    int size();

    boolean isEmpty();

    boolean equals(Object o);

    int hashCode();
}
