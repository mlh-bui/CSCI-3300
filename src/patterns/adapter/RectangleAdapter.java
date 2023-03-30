package patterns.adapter;

public class RectangleAdapter implements IRectanglePointSize {
    // make old object compatible with new object, will be the parameter
    private IRectangleTwoPoints rectangle;

    public RectangleAdapter(IRectangleTwoPoints rectangle) {
        // constructor method
        this.rectangle = rectangle;
    }

    @Override
    public int getX() {
        // returns x
        return rectangle.getX1();
    }

    @Override
    public int getY() {
        // returns y
        return rectangle.getY1();
    }

    @Override
    public int getWidth() {
        // returns width
        return rectangle.getX2() - rectangle.getX1();
    }

    @Override
    public int getHeight() {
        // returns height
        return rectangle.getY2() - rectangle.getY1();
    }

    @Override
    public String toString() {
        return "Rectangle (x=" + this.getX()+ ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
    }
}
