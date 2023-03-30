// V.2 Project: Taxify
// Marissa Bui - CSCI 3300 - 2/17

package taxify1;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements IVehicle {
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

    /** Route using ILocaiton */
    private List<ILocation> route;

    /** Basic Constructor for Vehicle */
    public Vehicle(int id, ILocation location) {
        this.id = id;
        this.service = null;
        this.status = VehicleStatus.FREE;
        this.location = location;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.statistics = new Statistics();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
    }

    /* Accessor & Mutator Methods */
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
    public IService getService() {
        return this.service;
    }

    @Override
    public IStatistics getStatistics() {
        return this.statistics;
    }

    @Override
    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    /**
     * Picks a service & sends Vehicle to pick up location
     * Sets route and changes vehicle status to pick up
     *
     * @param service
     */
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"

        this.service = service;
        this.destination = service.getPickupLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.PICKUP;
    } // method pickService

    /**
     * Service to drop-off location
     * Updates vehicle driving route & status to service
     */
    @Override
    public void startService() {
        this.destination = this.service.getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    } // method startService

    /**
     * Calculates cost of ride & updates statistics
     * Sets vehicle status to free
     */
    @Override
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

        // set service to null, and status to "free"

        this.service = null;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.FREE;
    } // method endService

    /**
     * When the Vehicle arrives at pick-up location the company is notified
     * Starts ride service
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the servic
        this.company.arrivedAtPickupLocation(this);
        this.startService();
    } // method notifyArrivalAtPickupLocation

    /**
     * When the Vehicle arrives at drop-off location the company is notified
     * Ends ride service
     */
    @Override
    public void notifyArrivalAtDropoffLocation() {
        this.company.arrivedAtDropoffLocation(this);
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
    } 

    /** Simulates a vehicle moving from location to location */
    @Override
    public void move() {
        // get the next location from the driving route

        this.location = this.route.get(0);
        this.route.remove(0);

        if (this.route.isEmpty()) {
            if (this.service == null) {
                // the vehicle continues its random route

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = setDrivingRouteToDestination(this.location, this.destination);
            }
            else {
                // checks if the vehicle has arrived to a pickup or drop off location

                ILocation origin = this.service.getPickupLocation();
                ILocation destination = this.service.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();

                }
            }
        }
    } // method move

    /**
     * Cost of service is the distance * vehicle rate
     *
     * @return int, distance travelled
     */
    @Override
    public int calculateCost() {
        // returns the cost of the service as the distance
        return service.calculateDistance();
    }

    /**
     * Shows locations on route as a string
     *
     * @return route as a string
     */
    @Override
    public String showDrivingRoute() {
        String s = "";

        for (ILocation l : this.route)
            s = s + l.toString() + " ";

        return s;
    } // method showDrivingRoute

    @Override
    public String toString() {
        return this.id + " at " + this.location + " driving to " + this.destination +
                ((this.status == VehicleStatus.FREE) ? " is free with path " + showDrivingRoute(): ((this.status == VehicleStatus.PICKUP) ? " to pickup user " + this.service.getUser().getId() : " in service "));
    } // method toString

    /**
     * Sets a vehicle's route to a destination
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

        for (int i=1; i<=dx; i++) {
            x1 = (x1 < x2) ? x1 + 1 : x1 - 1;

            route.add(new Location(x1, y1));
        }

        for (int i=1; i<=dy; i++) {
            y1 = (y1 < y2) ? y1 + 1 : y1 - 1;

            route.add(new Location(x1, y1));
        }

        return route;
    } // method setDrivingRouteToDestination

} // abstract class Vehicle