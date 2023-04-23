// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300
package v2;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements IVehicle {
    /** Vehicle Id */
    private int id;

    /** Taxi Company Object */
    private ITaxiCompany company;

    /** Service */
    private List<IService> service;

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
    public Vehicle(int id, ILocation location) {
        this.id = id;
        this.service = new ArrayList<>();
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
    public List<IService> getServices() {
        return this.service;
    }

    @Override
    public void setService(List<IService> service) {
        this.service = service;
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

    /**
     * Picks a service & sends Vehicle to pick up location
     * Sets route and changes vehicle status to pick up
     *
     * @param service
     */
    @Override
    public void pickService(IService service) {
        // pick a service, set destination to the service pickup location, and status to "pickup"

        this.service.add(service);
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
        this.destination = this.service.get(0).getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.SERVICE;
    } // method startService


    public void startSharedService() {
        this.destination = this.service.get(0).getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.SHARED_SERVICE;
    } // method startService

    /**
     * Calculates cost of ride & updates statistics
     * Sets vehicle status to free
     */
    @Override
    public void endService() {

        // if the service is rated by the user, update statistics
        // includes share service

        for (IService s : service) {
            this.statistics.updateBilling(this.calculateCost());
            this.statistics.updateServices();

            if (s.getStars() != 0) {
                this.statistics.updateStars(s.getStars());
                this.statistics.updateReviews();
            }

            this.statistics.updateDistance(s.calculateDistance());
        }

        if(this.service.size() == 1) {
            this.service = null;
            this.destination = ApplicationLibrary.randomLocation(this.location);
            this.route = setDrivingRouteToDestination(this.location, this.destination);
            this.status = VehicleStatus.FREE;
        } else {
            this.service.remove(0);
            this.destination = getService().getDropoffLocation();
            this.route = setDrivingRouteToDestination(this.location,this.destination);
        }

    } // method endService

    /**
     * When the Vehicle arrives at pick-up location the company is notified
     * Starts ride service
     */
    @Override
    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the service

        this.company.arrivedAtPickupLocation(this);
        this.startService();

    } // method notifyArrivalAtPickupLocation

    @Override
    public void notifyArrivalAtSecondaryPickUpLocation() {
        this.company.arrivedAtSecondaryPickupLocation(this);
        this.startSharedService();
    }

    /**
     * When the Vehicle arrives at drop-off location the company is notified
     * Ends ride service
     */
    @Override
    public void notifyArrivalAtDropOffLocation() {
        this.company.arrivedAtDropOffLocation(this);
        this.endService();
    } // method notifyArrivalAtDropOffLocation

    @Override
    public void notifyArrivalAtSecondaryDropOffLocation() {
        this.company.arrivedAtSecondaryDropOffLocation(this);
        endService();
    }

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
        this.location = this.route.get(0);
        this.route.remove(0);

        if (this.route.isEmpty()) {
            if (this.service == null || this.service.isEmpty() ) {
                // the vehicle continues its random drive

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = setDrivingRouteToDestination(this.location, this.destination);
            }
            // if there is more than one service for the vehicle
            else {
                IService currentService = this.getService();

                // get origin and destination of current service
                ILocation origin = currentService.getPickupLocation();
                ILocation destination = currentService.getDropoffLocation();

                // for multiple services aka shared services
                if (this.service.size() > 1) {

                    origin = this.service.get(0).getPickupLocation();
                    destination = this.service.get(0).getDropoffLocation();
                    System.out.println(destination.toString() + "DESTINATION SHOULD BE OLD");

                    ILocation secondDropOff;
                    ILocation secondPickUp = currentService.getPickupLocation();
                    secondDropOff = currentService.getDropoffLocation();

                    // notify when vehicle arrives at secondary pickup (current service pickup location)
                    if (ApplicationLibrary.isSameLocation(this.location, secondPickUp)) {

                        notifyArrivalAtSecondaryPickUpLocation();

                    } else if(ApplicationLibrary.isSameLocation(this.location, secondDropOff)) {

                        notifyArrivalAtSecondaryDropOffLocation();

                    }
                }

                // notify when vehicle arrives at pickup or destination
                if (ApplicationLibrary.isSameLocation(this.location,origin)) {

                    notifyArrivalAtPickupLocation();

                } else if (ApplicationLibrary.isSameLocation(this.location,destination)) {

                    notifyArrivalAtDropOffLocation();

                }
            }
        }
    } // method move


    public IService getService() {
        if(getStatus() != VehicleStatus.FREE) {
            return this.service.get(this.service.size() - 1); // latest service
        } else {
            return null;
        }
    } // method getService

    /**
     * Cost of service is the distance * vehicle rate
     *
     * @return int, distance travelled
     */
    @Override
    public int calculateCost() {
        int cost = 0;
        for(IService s : service) {
            if(this.service.size() > 1) {
                cost += (int) (s.calculateDistance() * 0.7);
            } else {
                cost = s.calculateDistance();
            }
        }

        return cost;
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

    @Override
    public String toString() {
        String s = "";
        if(this.status == VehicleStatus.FREE) {
            s = " is free with path " + showDrivingRoute();
        } else if (this.status == VehicleStatus.PICKUP) {
            s = " to pick up user " + this.getService().getUser().getId();
        } else if (this.status == VehicleStatus.SHARED_SERVICE) {
            s = " in shared service";
        } else if (this.status == VehicleStatus.SERVICE) {
            s = " in regular service " + this.getService().getUser().getId();
        }
        return this.id + " at " + this.location + " driving to " + this.destination + s;

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

    private ILocation closestDropOff(ILocation dropOff1, ILocation dropOff2) {
        ILocation closest = dropOff1;
        int distance1 = ApplicationLibrary.distance(getLocation(),dropOff1);
        int distance2 = ApplicationLibrary.distance(getLocation(),dropOff2);

        if(distance1 > distance2) {
            closest = dropOff2;
        }

        return closest;
    }

    private ILocation farthestDropOff(ILocation dropOff1, ILocation dropOff2) {
        ILocation closest = dropOff1;
        int distance1 = ApplicationLibrary.distance(getLocation(),dropOff1);
        int distance2 = ApplicationLibrary.distance(getLocation(),dropOff2);

        if(distance1 < distance2) {
            closest = dropOff2;
        }

        return closest;
    }

} // class Vehicle