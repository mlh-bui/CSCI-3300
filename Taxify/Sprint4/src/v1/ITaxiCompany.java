// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300
package v1;

public interface ITaxiCompany {

    String getName();
    int getTotalServices();
    boolean provideService(int user);
    boolean provideSharedService(int user);
    void arrivedAtPickupLocation(IVehicle vehicle);
    void arrivedAtSecondaryPickupLocation(IVehicle vehicle);
    void arrivedAtDropOffLocation(IVehicle vehicle);
    void addObserver(IObserver observer);
    void notifyObserver(String message);
    boolean cancelService(int user);


} // interface ITaxiCompany