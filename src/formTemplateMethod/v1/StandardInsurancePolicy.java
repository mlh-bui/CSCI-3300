package formTemplateMethod.v1;

public class StandardInsurancePolicy extends InsurancePolicy {

    public StandardInsurancePolicy(double base) {
        super(base);
    }

    @Override
    public double getAmount() {
        double base = this.getBase();
        double tax = base * 0.15;

        return base + tax;
    }


}
