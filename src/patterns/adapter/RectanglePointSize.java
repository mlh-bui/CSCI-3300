package patterns.adapter;

public class RectanglePointSize implements IRectanglePointSize {
    private int x;
    private int y;
    private int width;
    private int height;

    public RectanglePointSize(int x, int y, int width, int height) {
        // constructor method
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getX() {
        // returns x
        return this.x;
    }

    @Override
    public int getY() {
        // returns y
        return this.y;
    }

    @Override
    public int getWidth() {
        // returns width
        return this.width;
    }

    @Override
    public int getHeight() {
        // returns height
        return this.height;
    }

    @Override
    public String toString() {
        return "Rectangle (x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height=" + this.getHeight() + ")";
    }
}