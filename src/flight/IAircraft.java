package flight;

public interface IAircraft {
    String getId();
    int getMiles();
    int addMiles(int m);
    void fly(int m);
}
