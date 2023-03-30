package patterns.state;

public class TestProgram {

    public static void main(String[] args) {

        Order order1 = new Order("001");
        Order order2 = new Order("002");

        System.out.println(order1.getStatus());
        order1.setNextStatus();
        System.out.println(order1.getStatus());
        order1.setNextStatus();
        System.out.println(order1.getStatus());
        order1.setNextStatus();
        System.out.println(order1.getStatus());

        order2.setNextStatus();
        System.out.println(order2.getStatus());

    }

}