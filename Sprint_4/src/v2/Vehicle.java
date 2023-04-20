package v2;
// V.2 Project: Taxify
// Marissa Bui - CSCI 3300 - 4/20

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle implements IVehicle {
    /** Vehicle Id */
    private int id;

    /** Taxi Company Object */
    private ITaxiCompany company;

    /** Service */
    private List<IService> service;
    //private IService service;

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

    // NEWLY CHANGED
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
    // NEWLY ADDED 4/9
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

    // NEWlY ADDED 4/9
    public void startSharedService() {
        this.destination = closestService().getDropoffLocation();
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

            if(s.getStars() != 0) {
                this.statistics.updateStars(s.getStars());
                this.statistics.updateReviews();
            }

            this.statistics.updateDistance(s.calculateDistance());
        }

        // set service to null, and status to "free"
        //this.service = null; // used to be above destination test

        if(this.status == VehicleStatus.SHARED_SERVICE) {
            this.destination = farthestService().getDropoffLocation();
            //this.status = VehicleStatus.FREE;
            System.out.println("YO");
            this.service = null;
            //this.route = setDrivingRouteToDestination(this.location, this.destination);

        } else {
            System.out.println("hello");
            this.destination = ApplicationLibrary.randomLocation(this.location);
            this.status = VehicleStatus.FREE;
            this.service = null;
        }

        //this.service = null;

        this.route = setDrivingRouteToDestination(this.location, this.destination);


        //this.route = setDrivingRouteToDestination(this.location, this.destination);



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

    // NEWLY ADDED 4/9
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
    public void notifyArrivalAtDropoffLocation() {
        this.company.arrivedAtDropoffLocation(this); // why? because the end service is here?
        company.notifyObserver(getStatus() + " Checking vehicle status");
        this.endService();
    } // method notifyArrivalAtDropOffLocation

    public void notifylAtSecondaryDropOffLocation() {
        this.company.arrivedAtSecondaryDropOffLocation(this);
        this.company.notifyObserver("second drop off");
        this.endService();

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
            else if (this.service.size() >= 2) {
                IService service = this.getService();

                ILocation origin = this.service.get(0).getPickupLocation();
                ILocation secondPickup = service.getPickupLocation();

                ILocation destination = closestService().getDropoffLocation();
                ILocation secondDestination = farthestService().getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == secondPickup.getX() && this.location.getY() == secondPickup.getY()) {

                    notifyArrivalAtSecondaryPickUpLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();

                } else if (this.location.getX() == secondDestination.getX() && this.location.getY() == secondDestination.getY()) {

                    notifylAtSecondaryDropOffLocation();

                }
                // if there is a single service
            } else {
                IService service = this.getService();

                ILocation origin = service.getPickupLocation();
                ILocation destination = service.getDropoffLocation();

                if (this.location.getX() == origin.getX() && this.location.getY() == origin.getY()) {

                    notifyArrivalAtPickupLocation();

                } else if (this.location.getX() == destination.getX() && this.location.getY() == destination.getY()) {

                    notifyArrivalAtDropoffLocation();

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
    }

    /**
     * Cost of service is the distance * vehicle rate
     *
     * @return int, distance travelled
     */
    @Override
    public int calculateCost() {
        // returns the cost of the service as the distance
        int cost = 0;
        int cost2 = 0;
        cost = service.get(0).calculateDistance();
        if(service.size() > 1) {
            cost2 = service.get(1).calculateDistance();
            //System.out.println(cost2);
            //cost *= 0.7;
        }
        cost += cost2;
        //System.out.println("Distance " + cost);

        return cost;
    }

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

    public IService closestService() {
        //ILocation destination, secondDestination;

        IService toReturn;

        int distanceFromDropOff = ApplicationLibrary.distance(getLocation(), this.service.get(0).getDropoffLocation());
        int distanceFromSecondDropoff = ApplicationLibrary.distance(getLocation(), this.service.get(1).getDropoffLocation());

        if(distanceFromDropOff > distanceFromSecondDropoff) {
            toReturn = this.service.get(1);
        } else {
            toReturn = this.service.get(0);
        }
        return toReturn;
    }

    public IService farthestService() {
        //ILocation destination, secondDestination;

        IService toReturn;

        int distanceFromDropOff = ApplicationLibrary.distance(getLocation(), this.service.get(0).getDropoffLocation());
        int distanceFromSecondDropoff = ApplicationLibrary.distance(getLocation(), this.service.get(1).getDropoffLocation());

        if(distanceFromDropOff < distanceFromSecondDropoff) {
            toReturn = this.service.get(1);
        } else {
            toReturn = this.service.get(0);
        }
        return toReturn;
    }

} // abstract class Vehicle