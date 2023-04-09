package taxify;

public interface ITaxiCompany {

    public String getName();
    public int getTotalServices();
    public boolean provideService(int user);
    public void arrivedAtPickupLocation(IVehicle vehicle);
    public void arrivedAtDropoffLocation(IVehicle vehicle);
    public void addObserver(IObserver observer);
    public void notifyObserver(String message);

} // interface ITaxiCompany