package patterns.adapter;

public class TestProgram {

    public static void main(String[] args) {

        IRectangleTwoPoints twoPoints = new RectangleTwoPoints(5, 5, 125, 55);

        System.out.println(twoPoints.toString());

        IRectanglePointSize onePoint1 = new RectanglePointSize(5, 5, 120, 50);

        System.out.println(onePoint1.toString());

        // See the difference where onePoint2 = type new Rectangle and created with adapter of old Rectangle v2
        IRectanglePointSize onePoint2 = new RectangleAdapter(twoPoints); // two points = type Two points to one point

        System.out.println(onePoint2.toString()); // finds width and height instead of two points

    }

}