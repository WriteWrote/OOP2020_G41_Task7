package hashset.OOP.obukhovaEV.main.set;

public interface IMultiSet<T> extends ISet<T> {
    /**
     * Returns a number of unique elements in this {@link IMultiSet<T>}
     * @return number of unique elements
     */
    int numberOfUniqueElements();

    /**
     * Returns a number, how much element presents in this {@link IMultiSet<T>}
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
