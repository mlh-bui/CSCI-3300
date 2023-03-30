package patterns.adapter;

public class RectangleTwoPoints implements IRectangleTwoPoints{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public RectangleTwoPoints(int x1, int y1, int x2, int y2) {
        // constructor method
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public int getX1() {
        // returns x1
        return x1;
    }

    @Override
    public int getY1() {
        // returns y1
        return y1;
    }

    @Override
    public int getX2() {
        // returns x2
        return x2;
    }

    @Override
    public int getY2() {
        // returns y2
        return y2;
    }

    @Override
    public String toString() {
        return "Rectangle (x1=" + this.getX1() + ", y1=" + this.getY1() + ", x2=" + this.getX2() + ", y2=" + this.getY2() + ")";
    }
}