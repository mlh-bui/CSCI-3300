// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300

import java.util.List;

public interface IUser {

    int getId();
    String getFirstName();
    String getLastName();
    boolean hasService();
    ILocation getLocation();
    void setLocation(ILocation location) ;
    void setHasService(boolean hasService);
    void requestService();
    void requestSharedService();
    void rateService(IService service);
    //boolean acceptShareRide();
    String toString();
    void setCompany(ITaxiCompany company);

    void cancelRide();
    void makeReservation();

    void move();
    String showUserRoute();

} // interface IUser