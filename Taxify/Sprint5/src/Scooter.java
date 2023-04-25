public class Scooter extends MicroVehicle {

    /** Basic Constructor for Scooter */
    public Scooter(int id, ILocation location) {
        super(id, location);
    }

    @Override
    public int calculateCost() {
        return (int) (super.calculateCost() * 0.75);
    }

    @Override
    public String toString() {
        return "Scooter " + super.toString();
    } // method toString

}
