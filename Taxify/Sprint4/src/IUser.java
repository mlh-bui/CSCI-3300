public interface IUser {

    int getId();
    String getFirstName();
    String getLastName();
    boolean getService();
    ILocation getLocation(); // NEWLY ADDED 4/9
    void setService(boolean service);
    void requestService();
    void requestSharedService(); // NEWLY ADDED
    void rateService(IService service);
    boolean acceptShareRide(); // NEWLY ADDED
    String toString();
    void setCompany(ITaxiCompany company);

} // interface IUser