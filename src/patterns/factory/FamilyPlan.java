package patterns.factory;

public class FamilyPlan implements IMobilePhonePlan {
    @Override
    public double getBill(int data) {
        double bill = 60.0;
        int dataLimit = 50;
        if(data > dataLimit) {
            int extra = data - dataLimit;
            bill += extra * 2.5;
        }
        return bill;
    }
}
