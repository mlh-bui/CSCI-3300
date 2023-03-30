package patterns.strategy.find;

public class FindFirst implements IFind {
    @Override
    public int getIndex(IItem[] items) {
        for (int i= 0; i <= items.length -1; i++) {
            if (items[i].isAvailable()) {
                return i;
            }
        }
        return -1;
    }
}
