package patterns.strategy.find;

public class FindLast implements IFind {
    @Override
    public int getIndex(IItem[] items) {
        for (int i=items.length-1; i>=0; i--) {
            if (items[i].isAvailable()) {
                return i;
            }
        }
        return -1;
    }
}
