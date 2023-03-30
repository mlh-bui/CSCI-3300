package flight;

public class Pilot implements IPilot{
    private String id;
    private int miles;

    public Pilot(String id, int miles) {
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

    public void flyAircraft(IAircraft a) {
        a.fly(getMiles());
    }
}