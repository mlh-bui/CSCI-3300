// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300

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

    /**
     * Reserves vehicle for user by changing status to booked
     *
     * @param service, assigned service to vehicle
     */
    @Override
    public void pickService(IService service) {
        this.status = VehicleStatus.BOOKED;
        this.service = service;
    } // method pickService

    /**
     * Service to drop-off location
     * Updates vehicle driving route & status to service
     */
    public void startService() {
        this.destination = this.service.getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    } // method startService

    /**
     * Calculates cost of ride & updates statistics
     * Sets vehicle status to free and ends service for the vehicle and user
     */
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

        // update user's location (they've been "dropped off")
        IUser currentUser = getService().getUser();
        currentUser.setLocation(this.location);
        currentUser.setService(null);

        // at the end of a service, the vehicle remains at drop off location
        this.service = null;
        this.destination = null;
        this.route = null;
        this.status = VehicleStatus.FREE;
    } // method end Service

    /** At the pick-up location the company is notified and a micro vehicle service starts */
    @Override
    public void notifyArrivalAtPickupLocation( ) {
        this.startService();
    } // method notifyArrivalAtPickUpLocation

    /** At the drop-off location the company is notified and the service ends */
    @Override
    public void notifyArrivalAtDropOffLocation() {
        this.company.arrivedAtMicroDropLocation(this);
        this.endService();
    } // method notifyArrivalAtDropOffLocation

    /**
     * Checks vehicle status for availability
     *
     * @return true, if vehicle is free
     */
    @Override
    public boolean isFree() {
        return this.status == VehicleStatus.FREE;
    } // method isFree

    /**
     * Move while the micro vehicle has a service
     */
    @Override
    public void move() {
        if(this.route != null) {
            this.location = this.route.get(0);
            this.route.remove(0);
        }

        if(this.service != null) {
            ILocation origin = getService().getPickupLocation();
            if(ApplicationLibrary.isSameLocation(this.service.getUser().getLocation(), origin)) {

                notifyArrivalAtPickupLocation();

            }

            // get origin and destination of current service
            ILocation destination = getService().getDropoffLocation();

            // notify when vehicle arrives at pickup or destination
           if (ApplicationLibrary.isSameLocation(getLocation(), destination)) {

                notifyArrivalAtDropOffLocation();

            }
        }
    } // method move

    /**
     * Cost of service is the distance * vehicle rate
     *
     * @return int, service cost
     */
    @Override
    public int calculateCost() {
        return this.service.calculateDistance();
    } // method calculateCost

    /**
     * Shows locations on route as a string
     *
     * @return route as a string
     */
    @Override
    public String showDrivingRoute() {
        StringBuilder s = new StringBuilder();

        for (ILocation l : this.route)
            s.append(l.toString()).append(" ");

        return s.toString();
    } // method showDrivingRoute

    /**
     * Represents the vehicle's status or route
     *
     * @return String, vehicle
     */
    @Override
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

    /**
     * Sets a micro vehicle's route to a destination
     * Uses location to get the vehicles coordinates
     *
     * @param location, ILocation
     * @param destination, ILocation
     * @return route, as List<ILocation>
     */
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
    } // method setDrivingRouteToDestination


    /* Accessors & Mutators */
    @Override
    public int getId() {
        return this.id;
    } // method getId

    @Override
    public ILocation getLocation() {
        return this.location;
    } // method getLocation

    @Override
    public ILocation getDestination() {
        return this.destination;
    } // method getDestination

    @Override
    public IStatistics getStatistics() {
        return this.statistics;
    } // method getStatistics

    @Override
    public VehicleStatus getStatus() {
        return this.status;
    } // method getStatus

    @Override
    public IService getService() {
        if (this.status != VehicleStatus.FREE) {
            return this.service;
        }
        return null;
    } // method getService

    // does not have multiple services
    @Override
    public List<IService> getServices() {
        return null;
    } // method getServices

    @Override
    public void setStatistics(IStatistics statistics) {
        this.statistics = statistics;
    } // method statistics

    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    } // method setCompany

} // class MicroVehicle