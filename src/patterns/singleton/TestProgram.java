package patterns.singleton;

public class TestProgram {

    public static void main(String[] args) {

        // Declare two shopping carts, add items to both carts, and show their contents

        ShoppingCart cart1, cart2;

        cart1 = ShoppingCart.getInstance();

        cart1.addItem("Item 1-A");
        cart1.addItem("Item 1-B");
        cart1.addItem("Item 1-C");

        System.out.println("Items in cart1\n");
        System.out.println(cart1.getItems());

        cart2 = ShoppingCart.getInstance();

        cart2.addItem("Item 2-A");
        cart2.addItem("Item 2-B");
        cart2.addItem("Item 2-C");

        System.out.println("Items in cart1 \n");
        System.out.println(cart1.getItems());

        System.out.println("Items in cart2 \n");
        System.out.println(cart2.getItems());


    }

}