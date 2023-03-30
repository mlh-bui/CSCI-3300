package patterns.factory;

public class BasicPlan implements IMobilePhonePlan {
    private int data;
    private int fare;

    public BasicPlan() {
        this.data = 10;
        this.fare = 15;
    }
    @Override
    public double getBill(int data) {
        return this.fare + ((data>this.data) ? (data - this.data) * 1.5 : 0);
    }
}
