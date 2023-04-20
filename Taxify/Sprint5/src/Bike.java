public class Bike extends Vehicle {

    /** Basic Constructor for Bike */
    public Bike(int id, ILocation location) {
        super(id, location);
    }


    @Override
    public int calculateCost() {
        return (int) (super.calculateCost() * 0.50);
    }

    @Override
    public String toString() {
        return "Bike " + super.toString();
    }
}
