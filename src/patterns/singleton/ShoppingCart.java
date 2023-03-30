package patterns.singleton;

import java.util.Vector;

/*
 * The Singleton pattern creates one single instance, the method getInstance() controls the access to this object
 */

public class ShoppingCart {
    private Vector<String> items;
    private static ShoppingCart instance = null;

    // the constructor is private

    private ShoppingCart() {
        this.items = new Vector<String>();
    }

    // getInstance() creates an object only if the instance is null, synchronized is used to prevent concurrent access to the object

    public static synchronized ShoppingCart getInstance() {
        if (instance == null)
            instance = new ShoppingCart();

        return instance;
    }

    public void addItem(String item) {
        this.items.add(item);
    }

    public String getItems() {
        String items = "";

        for (String item : this.items)
            items = items + item + "\n";

        return items;
    }
}
