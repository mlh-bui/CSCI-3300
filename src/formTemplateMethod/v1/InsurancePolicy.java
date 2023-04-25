package formTemplateMethod.v1;

public abstract class InsurancePolicy implements IInsurancePolicy {
    private double base;

    public InsurancePolicy(double base) {
        this.base = base;
    }

    @Override
    public double getBase() {
        return this.base;
    }

    @Override
    public abstract double getAmount();
}
