import java.util.ArrayList;
import java.util.List;

public class MicroVehicle implements IVehicle  {
    /** Vehicle Id */
    private int id;

    /** Taxi Company Object */
    private ITaxiCompany company;

    /** Service */
    private IService service;

    /** Vehicle status */
    private VehicleStatus status;

    /** Location */
    private ILocation location;

    /** Destination */
    private ILocation destination;

    /** Statistics */
    private IStatistics statistics;

    /** Route using ILocation */
    private List<ILocation> route;


    /** Basic Constructor for Vehicle */
    public MicroVehicle(int id, ILocation location) {
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;
        this.destination = null;
        this.statistics = new Statistics();
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public ILocation getLocation() {
        return this.location;
    }

    @Override
    public ILocation getDestination() {
        return this.destination;
    }

    @Override
    public IStatistics getStatistics() {
        return this.statistics;
    }

    @Override
    public VehicleStatus getStatus() {
        return this.status;
    }

    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    @Override
    public void pickService(IService service) {
        this.status = VehicleStatus.BOOKED;
        this.service = service;
    }

    public void startService() {
        this.destination = this.service.getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.service.getUser().setService(null);
        this.status = VehicleStatus.SERVICE;
    }

    public void endService() {
        // update vehicle statistics

        this.statistics.updateBilling(this.calculateCost());
        this.statistics.updateDistance(this.service.calculateDistance());
        this.statistics.updateServices();

        // if the service is rated by the user, update statistics

        if (this.service.getStars() != 0) {
            this.statistics.updateStars(this.service.getStars());
            this.statistics.updateReviews();
        }

        /*// set service to null, and status to "free"
        this.service.getUser().setLocation(this.service.getDropoffLocation());
        System.out.println(this.service.getUser().getLocation() + " location of user from end service micro");

        this.service.getUser().setDestination(ApplicationLibrary.randomLocation(this.location));
        this.service.getUser().setRoute(setDrivingRouteToDestination(this.location,this.destination));*/

        IUser currentUser = getService().getUser();
        currentUser.setLocation(this.location);
        currentUser.setDestination(ApplicationLibrary.randomLocation(this.location));
        currentUser.setRoute(setDrivingRouteToDestination(currentUser.getLocation(), currentUser.getDestination()));
        currentUser.setService(null);

        this.service = null;
        this.route = null;
        //this.destination = ApplicationLibrary.randomLocation(this.location);
        this.status = VehicleStatus.FREE;
    }

    @Override
    public void notifyArrivalAtPickupLocation( ) {
        this.company.userArrivesAtMicroVehicleLocation(this.service.getUser());
        this.startService();
    }

    @Override
    public void notifyArrivalAtDropOffLocation() {
        this.company.arrivedAtDropOffLocation(this);
        this.endService();
    }

    @Override
    public boolean isFree() {
        return this.status == VehicleStatus.FREE;
    }

    @Override
    public void move() {
        if(this.route != null) {
            this.location = this.route.get(0);
            this.route.remove(0);
        }

        if(this.service != null) {
            // get origin and destination of current service
            ILocation destination = getService().getDropoffLocation();

            // notify when vehicle arrives at pickup or destination
           if (ApplicationLibrary.isSameLocation(getLocation(), destination)) {

                notifyArrivalAtDropOffLocation();
                System.out.println("whoa");

            }
        }
    } // method move

    @Override
    public int calculateCost() {
        return this.service.calculateDistance();
    }

    @Override
    public String showDrivingRoute() {
        StringBuilder s = new StringBuilder();

        for (ILocation l : this.route)
            s.append(l.toString()).append(" ");

        return s.toString();
    }

    public String toString() {
        String s = " ";

        if(this.status == VehicleStatus.BOOKED) {
            s = " is booked for User " + this.getService().getUser().getId();
        } else if (this.status == VehicleStatus.FREE) {
            s = " is free";
        } else if (this.status == VehicleStatus.SERVICE) {
            s = " is in service with User " + this.getService().getUser().getId() + " to " + getDestination();
        }

        return getId() + " at " + getLocation() + s;
    } // method toString

    public IService getService() {
        if (this.status != VehicleStatus.FREE) {
            return this.service;
        }
        return null;
    }

    public IService getServices() {
        return this.service;
    }

    @Override
    public void setStatistics(IStatistics statistics) {
        this.statistics = statistics;
    }

    private List<ILocation> setDrivingRouteToDestination(ILocation location, ILocation destination) {
        List<ILocation> route = new ArrayList<ILocation>();

        int x1 = location.getX();
        int y1 = location.getY();

        int x2 = destination.getX();
        int y2 = destination.getY();

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);

        for (int i = 1; i <= dx; i++) {
            x1 = (x1 < x2) ? x1 + 1 : x1 - 1;

            route.add(new Location(x1, y1));
        }

        for (int i = 1; i <= dy; i++) {
            y1 = (y1 < y2) ? y1 + 1 : y1 - 1;

            route.add(new Location(x1, y1));
        }

        return route;
    }

}