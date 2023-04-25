package formTemplateMethod.v1;

public class PrimeInsurancePolicy extends InsurancePolicy {
    public PrimeInsurancePolicy(double base) {
        super(base);
    }

    public double getAmount() {
        double base = this.getBase() * 0.9;
        double tax = base * 0.10;

        return base + tax;
    }
}
