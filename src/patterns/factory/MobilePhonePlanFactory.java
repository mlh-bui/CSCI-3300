package patterns.factory;

public class MobilePhonePlanFactory {
    public static IMobilePhonePlan newPlan(String type) {
        if(type.equals("Family")) {
            return new FamilyPlan();
        } else if(type.equals("Business")) {
            return new BuisnessPlan();
        } else {
            return new BasicPlan();
        }
    }
}
