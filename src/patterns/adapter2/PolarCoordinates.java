package patterns.adapter2;

public class PolarCoordinates implements IPolarCoordinates {
    private double r;
    private double O;

    public PolarCoordinates(double r, double O) {
        this.r = r;
        this.O = O;
    }

    public double getRadius() {
        return this.r;
    }

    public double getAngle() {
        return this.O;
    }

    public String toString() {
        return String.format("(%.2f, %.2f)", getRadius(), getAngle());
    }
}
