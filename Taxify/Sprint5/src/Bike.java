public class Bike extends MicroVehicle {


    public Bike(int id, ILocation location) {
        super(id, location);
    }

    public int calculateCost() {
        return (int) (super.calculateCost() * 0.50);
    }

    public String toString() {
        return "Bike " + super.toString();
    } // method toString

} // class Bike
