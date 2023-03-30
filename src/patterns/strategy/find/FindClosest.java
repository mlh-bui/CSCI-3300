package patterns.strategy.find;

public class FindClosest implements IFind {

    @Override
    public int getIndex(IItem [] items) {
        int index = 0;

        for (int i=1; i<items.length; i++)
            if (items[i].isAvailable() && items[i].getDistance() < items[index].getDistance()) {
                index = i;
            }

        return (items[index].isAvailable()) ? index : -1;
    }

}
