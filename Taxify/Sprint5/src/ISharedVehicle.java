// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300

import java.util.List;

public interface ISharedVehicle {
    void startSharedService();
    void notifyArrivalAtSecondaryPickUpLocation();
    void notifyArrivalAtSecondaryDropOffLocation();
    void setService(List<IService> service);
} // interface ISharedVehicle
