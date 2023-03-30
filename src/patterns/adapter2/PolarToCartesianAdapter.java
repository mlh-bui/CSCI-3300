package patterns.adapter2;

public class PolarToCartesianAdapter implements ICartesianCoordinates {
    private IPolarCoordinates polar;

    public PolarToCartesianAdapter(IPolarCoordinates coordinates) {
        this.polar = coordinates;
    }

    public double getX() {
        return polar.getRadius()* Math.cos(polar.getAngle());
    }

    public double getY() {
        return polar.getRadius()* Math.sin(polar.getAngle());
    }

    public String toString() {
        return String.format("(%.2f, %.2f)",getX(),getY());
    }
}
