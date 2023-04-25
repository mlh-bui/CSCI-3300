package formTemplateMethod.v2;

public class PrimeInsurancePolicy extends InsurancePolicy {
    public PrimeInsurancePolicy(double base) {
        super(base);
    }

    @Override
    public double getBase() {
        return super.getBase() * 0.90;
    }

    public double getTax() {
        return this.getBase() * 0.10;
    }
}
