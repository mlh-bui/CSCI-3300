// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300

public interface IService {

    IUser getUser();
    ILocation getPickupLocation();
    ILocation getDropoffLocation();
    int getStars();
    void setStars(int stars);
    int calculateDistance();
    String toString();
    IVehicle getVehicle();
    void setVehicle(IVehicle vehicle);

} // interface IService