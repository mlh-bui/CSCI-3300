package formTemplateMethod.v1;

public class TestProgram {

    public static void main(String[] args) {

        IInsurancePolicy p1 = new StandardInsurancePolicy(100);
        IInsurancePolicy p2 = new PrimeInsurancePolicy(100);

        System.out.println("Standard policy  Amount " + p1.getAmount());
        System.out.println("Prime policy     Amount " + p2.getAmount());

    }
}
