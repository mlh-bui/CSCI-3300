package flight;

public interface IPilot {
    String getId();
    int getMiles();
    int addMiles(int m);
    void flyAircraft(IAircraft a);
}
