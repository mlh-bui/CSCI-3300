package patterns.factory;

public class BuisnessPlan implements IMobilePhonePlan{
    @Override
    public double getBill(int data) {
        return 150;
    }
}
