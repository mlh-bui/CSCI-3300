package flight;

public class Helicopter extends Aircraft {
    public Helicopter(String id, int miles) {
        super(id, miles);
    }

    @Override
    public void fly(int miles) {
        //System.out.println("Flying 200 mph");
    }
}
