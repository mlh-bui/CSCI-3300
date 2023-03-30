package patterns.adapter2;

public class CartesianCoordinates implements ICartesianCoordinates {
    private double r;
    private double O;
    private double x;
    private double y;

    public CartesianCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.r * Math.cos(O);
    }

    public double getY() {
        return this.r * Math.sin(O);
    }

    public String toString() {
        return String.format("(%.2f, %.2f)",getX(),getY());
    }
}
