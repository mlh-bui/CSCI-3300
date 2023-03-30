package flight;

public abstract class Aircraft implements IAircraft {
    private String id;
    private int miles;

    public Aircraft(String id, int miles) {
        this.id = id;
        this.miles = miles;
    }

    public String getId() {
        return this.id;
    }
    public int getMiles() {
        return this.miles;
    }
    public int addMiles(int m) {
        return this.miles + m;
    }

    public void fly(int miles) {
        //System.out.println("Flying 250 mph");
        addMiles(miles);
    }
}