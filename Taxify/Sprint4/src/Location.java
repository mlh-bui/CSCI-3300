// V.2 Project: Taxify
// Marissa Bui - CSCI 3300 - 2/17

public class Location implements ILocation {
    /** x-coordinate for map */
    private int x;

    /** y-coordinate for map */
    private int y;

    /** Basic constructor */
    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /* Accessors & Mutators */
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }

} // class Location
