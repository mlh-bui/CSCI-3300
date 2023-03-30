package patterns.iterator;

public interface IArrayIterator<T> {

    public boolean hasNext();
    public T next();

}