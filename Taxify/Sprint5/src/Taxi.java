// Marissa Bui - CSCI 3300 - 2/17

public class Taxi extends Vehicle {

    /** Basic Constructor for Taxi */
    public Taxi(int id, ILocation location) {
        super(id, location);
    }

    /**
     * Calculates cost for taxi ride
     *
     * @return int, cost * taxi rate
     */
    @Override
    public int calculateCost() {
        return super.calculateCost() * 2;
    }

    @Override
    public String toString() {
        return "Taxi    " + super.toString();
    }
}