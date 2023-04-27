import java.util.ArrayList;
import java.util.List;

public interface IVehicle {

    int getId();
    ILocation getLocation();
    ILocation getDestination();
    IStatistics getStatistics();
    VehicleStatus getStatus();
    void setCompany(ITaxiCompany company);
    void pickService(IService service);
    void startService();
    void endService();
    void notifyArrivalAtPickupLocation();
    void notifyArrivalAtDropOffLocation();
    boolean isFree();
    void move();
    int calculateCost();
    String showDrivingRoute();
    String toString();
    IService getService();
    void setStatistics(IStatistics statistics);
}