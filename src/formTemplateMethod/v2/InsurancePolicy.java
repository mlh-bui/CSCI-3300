package formTemplateMethod.v2;

public abstract class InsurancePolicy implements IInsurancePolicy {
    private double base;

    public InsurancePolicy(double base) {
        this.base = base;
    }

    @Override
    public double getAmount() {
        return this.getBase() + this.getTax();
    }

    @Override
    public double getBase() {
        return this.base;
    }

    @Override
    public abstract double getTax();

}

