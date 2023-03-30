package patterns.strategy.find;

public class TestProgram {

    // COMPARE WITH THE JAVA INTERFACE COMPARABLE
    // Java comparable allows us to compare objects
    public static void printItems(IItem [] items, IFind find) throws CloneNotSupportedException {
        IItem [] clonedItems = new Item[items.length];

        for (int i=0; i<items.length; i++) {
            clonedItems[i] = (IItem) items[i].clone();
        }

        for (int i=1; i<=5; i++) {
            int index = find.getIndex(clonedItems);

            if (index != -1) {
                System.out.println(clonedItems[index].toString());

                clonedItems[index].setAvailable(false);
            }
        }
    }

    public static void main(String[] args) {

        // get items from the container using four different algorithms: FindFirst, FindLast, FindClosest, FindRandom

        IItem [] items = { new Item("01", 5, true),  new Item("02", 9, true),  new Item("03", 2, false), new Item("04", 7, true),
                new Item("05", 4, true),  new Item("06", 2, true),  new Item("07", 1, false), new Item("08", 3, true),
                new Item("09", 6, true),  new Item("10", 2, true),  new Item("11", 5, true),  new Item("12", 3, false) };

        System.out.println("Items \n");

        for (IItem item : items)
            System.out.println(item.toString());

        try {

            System.out.println("\nFind first \n");
            printItems(items, new FindFirst());

            System.out.println("\nFind last \n");
            printItems(items, new FindLast());

            System.out.println("\nFind closest \n");
            printItems(items, new FindClosest());

            System.out.println("\nFind random \n");
            printItems(items, new FindRandom());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
