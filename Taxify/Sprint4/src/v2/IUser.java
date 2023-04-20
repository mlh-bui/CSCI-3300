// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300
package v2;

public interface IUser {

    int getId();
    String getFirstName();
    String getLastName();
    boolean hasService();
    ILocation getLocation();
    void setLocation(ILocation location) ;
    void setService(boolean service);
    void requestService();
    void requestSharedService();
    void rateService(IService service);
    //boolean acceptShareRide();
    String toString();
    void setCompany(ITaxiCompany company);

    boolean cancelRide();

} // interface IUser