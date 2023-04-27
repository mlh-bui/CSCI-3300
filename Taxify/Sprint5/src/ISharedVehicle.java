import java.util.List;

public interface ISharedVehicle {
    void startSharedService();
    void notifyArrivalAtSecondaryPickUpLocation();
    void notifyArrivalAtSecondaryDropOffLocation();
    List<IService> getServices();
    void setService(List<IService> service);
}
