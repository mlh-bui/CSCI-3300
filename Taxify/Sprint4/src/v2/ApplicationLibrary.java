// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300

package v2;

import java.util.Random;

public class ApplicationLibrary {
    private static final int width = 10;
    private static final int height = 10;

    public static int rand() {
        Random random = new Random();

        return random.nextInt(9767);
    }

    public static int rand(int max) {
        Random random = new Random();

        return random.nextInt(9767) % max;
    }

    public static int distance(ILocation a, ILocation b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    public static ILocation randomLocation() {
        return new Location(rand(width), rand(height));
    }

    public static ILocation randomLocation(ILocation location) {
        ILocation destination;

        do {

            destination = new Location(rand(width), rand(height));

        } while (distance(location, destination) < 3);

        return destination;
    }

    public static boolean isSameLocation(ILocation l1, ILocation l2) {
        return l1.getX() == l2.getX() && l1.getY() == l2.getY();
    }

} // class Application Library