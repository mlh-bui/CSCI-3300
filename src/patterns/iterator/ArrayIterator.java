package patterns.iterator;

public class ArrayIterator<T> implements IArrayIterator<T> {
    private T [] data;
    private int index;

    public ArrayIterator(T [] data) {
        this.data = data;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return (this.index < this.data.length) ? true : false;
    }

    @Override
    public T next() {
        return (this.hasNext()) ? this.data[this.index++] : null;
    }

}