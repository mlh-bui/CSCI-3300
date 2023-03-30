package flight;

public class Airplane extends Aircraft {
    public Airplane(String id, int miles) {
        super(id, miles);
    }

    @Override
    public void fly(int miles) {
        //System.out.println("Flying 300 mph");
    }
}