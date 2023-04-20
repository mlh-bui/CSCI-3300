public interface ITaxiCompany {

    String getName();
    int getTotalServices();
    boolean provideService(int user);
    public boolean provideSharedService(int user);
    void arrivedAtPickupLocation(IVehicle vehicle);
    void arrivedAtSecondaryPickupLocation(IVehicle vehicle); // NEWLY ADDED 4/9
    void arrivedAtDropoffLocation(IVehicle vehicle);
    void addObserver(IObserver observer);
    void notifyObserver(String message);

} // interface ITaxiCompany