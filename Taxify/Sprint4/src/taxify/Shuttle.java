// V.2 Project: Taxify
// Marissa Bui - CSCI 3300 - 2/17

package taxify;

public class Shuttle extends Vehicle {

    /** Basic Constructor for Shuttle */
    public Shuttle(int id, ILocation location) {
        super(id, location);
    }

    /**
     * Calculates cost for shuttle ride
     *
     * @return int, cost * shuttle rate
     */
    @Override
    public int calculateCost() {
        return (int) (super.calculateCost() * 1.5);
    }

    @Override
    public String toString() {
        return "Shuttle " + super.toString();
    }
}