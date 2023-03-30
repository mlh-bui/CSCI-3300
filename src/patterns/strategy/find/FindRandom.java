package patterns.strategy.find;

import java.util.Random;

public class FindRandom implements IFind {
    @Override
    public int getIndex(IItem[] items) {
        int index = 0;
        int tests = 0;

        Random random = new Random();

        do {
                index = random.nextInt(items.length);
                tests++;
        } while (!items[index].isAvailable() && tests <= 50);

        return (items[index].isAvailable()) ? index : -1;
    }
}
