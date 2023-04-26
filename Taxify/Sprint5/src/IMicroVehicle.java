import java.util.List;

public interface IMicroVehicle {
    MicroVehicleStatus getMicroStatus();
    void move();
    void notifyArrivalAtDropOffLocation();
    void notifyUserArrivalAtPickupLocation();
    void endService();
    void startService();
    void bookedService(IUser user);
    void setCompany(ITaxiCompany company);
    IStatistics getStatistics();
    ILocation getDestination();
    ILocation getLocation();
    int getId();
    void setStatistics(IStatistics statistics);
    String showDrivingRoute();
    int calculateCost();
}
