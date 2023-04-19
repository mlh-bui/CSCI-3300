package v1;

import java.util.List;

public interface IVehicle {

    int getId();
    ILocation getLocation();
    ILocation getDestination();
    IStatistics getStatistics();
    VehicleStatus getStatus();  // NEWLY ADDED 4/9
    void setService(List<IService> service); // NEWLY ADDED 4/10
    void setCompany(ITaxiCompany company);

    void pickService(IService service); // method pickService

    void startService();
    void endService();
    void notifyArrivalAtPickupLocation();
    void notifyArrivalAtSecondaryPickUpLocation(); // NEWLY ADDED 4/9
    void notifyArrivalAtDropoffLocation();
    boolean isFree();
    boolean isInService();  // NEWLY ADDED 4/9
    void move();
    int calculateCost();
    String showDrivingRoute();
    String toString();

    IService getService(); // ADDED
    List<IService> getServices(); // ADDED

}