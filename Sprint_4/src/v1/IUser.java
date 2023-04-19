package v1;

public interface IUser {

    int getId();
    String getFirstName();
    String getLastName();
    boolean hasService();
    ILocation getLocation(); // NEWLY ADDED 4/9
    void setLocation(ILocation location) ; // NEWLY ADDED 4/19
    void setService(boolean service);
    void requestService();
    void requestSharedService(); // NEWLY ADDED
    void rateService(IService service);
    //boolean acceptShareRide(); // NEWLY ADDED
    String toString();
    void setCompany(ITaxiCompany company);

    boolean cancelRide();

} // interface IUser