public interface ISharedVehicle {
    void startSharedService();
    void notifyArrivalAtSecondaryPickUpLocation();
    void notifyArrivalAtSecondaryDropOffLocation();
}
