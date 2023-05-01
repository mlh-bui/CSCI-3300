// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300
public class Bike extends MicroVehicle {

    /** Basic Constructor for Bike */
    public Bike(int id, ILocation location) {
        super(id, location);
    }

    /**
     * Calculates cost for bike ride
     *
     * @return int, cost * bike rate
     */
    public int calculateCost() {
        return (int) (super.calculateCost() * 0.50);
    }

    public String toString() {
        return "Bike " + super.toString();
    } // method toString

} // class Bike
