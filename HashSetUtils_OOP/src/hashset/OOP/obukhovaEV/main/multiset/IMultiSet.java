package hashset.OOP.obukhovaEV.main.multiset;

import hashset.OOP.obukhovaEV.main.set.ISet;

public interface IMultiSet<T> extends ISet<T> {
    /**
     * Adds a number of occurrences of an element to this {@link IMultiSet}
     *
     * @param element     element
     * @param occurrences number of occurrences
     * @return always true
     */
    boolean add(T element, int occurrences);

    /**
     * Returns a number of unique elements in this {@link IMultiSet<T>}
     *
     * @return number of unique elements
     */
    int numberOfUniqueElements();

    /**
     * Returns a number, how much exemplars of this element presents in this {@link IMultiSet<T>}
     *
     * @param element - testing element
     * @return multiplicity
     */
    int getMultiplicity(T element);

    /**
     * Returns a {@link ISet<T>} with all elements presenting in this particular {@link IMultiSet<T>}
     * Each element presents only once in new collection.
     *
     * @return {@link ISet<T>}
     */
    ISet<T> toSet();
}
