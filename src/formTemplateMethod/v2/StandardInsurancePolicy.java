package formTemplateMethod.v2;

public class StandardInsurancePolicy extends InsurancePolicy {
    public StandardInsurancePolicy(double base) {
        super(base);
    }

    @Override
    public double getBase() {
        return super.getBase();
    }

    public double getTax() {
        return this.getBase() * 0.15;
    }
}
