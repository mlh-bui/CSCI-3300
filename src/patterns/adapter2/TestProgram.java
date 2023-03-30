package patterns.adapter2;

public class TestProgram {
    public static void main(String[] args) {
        IPolarCoordinates polar1 = new PolarCoordinates(4,Math.PI/4); // should be 2.82, 2.82
        IPolarCoordinates polar2 = new PolarCoordinates(3,Math.PI/3);

        ICartesianCoordinates toCartesian1 = new PolarToCartesianAdapter(polar1);
        ICartesianCoordinates toCartesian2 = new PolarToCartesianAdapter(polar2);

        System.out.println(toCartesian1.toString());
        System.out.println(toCartesian2.toString());
    }
}
