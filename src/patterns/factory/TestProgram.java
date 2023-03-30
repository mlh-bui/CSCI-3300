package patterns.factory;

public class TestProgram {
    public static void main(String[] args) {
        // instead of creating objects inside the test program you create them in the factory

        IMobilePhonePlan family = MobilePhonePlanFactory.newPlan("Family");
        IMobilePhonePlan business = MobilePhonePlanFactory.newPlan("Business");
        IMobilePhonePlan basic = MobilePhonePlanFactory.newPlan("Basic");

        System.out.println("The Family plan with 80 GB pays " + family.getBill(80) + " eur.");
        System.out.println("The Family plan with 60 GB pays " + family.getBill(60) + " eur.");
        System.out.println("The Family plan with 45 GB pays " + family.getBill(45) + " eur.");

        System.out.println("The Basic plan with 20 GB pays " + basic.getBill(20) + " eur.");
        System.out.println("The Basic plan with 15 GB pays " + basic.getBill(15) + " eur.");
        System.out.println("The Basic plan with 5 GB pays " + basic.getBill(5) + " eur.");

        System.out.println("The Business plan with 100 GB pays " + business.getBill(100) + " eur.");

    }
}