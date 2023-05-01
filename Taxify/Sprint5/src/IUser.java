// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300
import java.util.List;

public interface IUser {

    int getId();
    String getFirstName();
    String getLastName();
    boolean hasService();
    ILocation getLocation();
    void setLocation(ILocation location) ;
    void requestService();
    void requestSharedService();
    void rateService(IService service);
    String toString();
    void setCompany(ITaxiCompany company);
    void cancelRide();
    void makeReservation();
    void move();
    String showUserRoute();
    void setService(IService service);
    IService getService();
    void setRoute(List<ILocation> route);
    List<ILocation> getRoute();

} // interface IUser