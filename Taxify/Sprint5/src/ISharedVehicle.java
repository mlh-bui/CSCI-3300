import java.util.List;

public interface ISharedVehicle {
    void startSharedService();
    void notifyArrivalAtSecondaryPickUpLocation();
    void notifyArrivalAtSecondaryDropOffLocation();
    void setService(List<IService> service);
}
