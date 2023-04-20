public interface IVehicle {

    int getId();
    ILocation getLocation();
    ILocation getDestination();
    IStatistics getStatistics();
    VehicleStatus getStatus();  // NEWLY ADDED 4/9
    IService getService(int index); // NEWLY ADDED 4/9
    void setService(IService service); // NEWLY ADDED 4/10
    void setCompany(ITaxiCompany company);

    void pickService(IService service); // method pickService
    void pickSharedService(IService service);

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

}