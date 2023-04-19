public interface IService {

    IUser getUser();
    ILocation getPickupLocation();
    ILocation getDropoffLocation();
    int getStars();
    void setStars(int stars);
    int calculateDistance();
    String toString();

    boolean isShared();

} // interface IService