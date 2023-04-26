// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300

import java.util.ArrayList;
import java.util.List;

public class User implements IUser {
    /** User ID */
    private int id;

    /** User first name */
    private String firstName;

    /** User last name */
    private String lastName;

    /** Taxi company */
    private ITaxiCompany company;

    /** User request for a service or not */
    private boolean hasService;

    /** User service */
    private IService service;

    /** User location to find location relative to a vehicle */
    private ILocation location;

    private ILocation destination;

    /** User route to Micro Vehicles */
    private List<ILocation> route;

    /** Basic constructor */
    public User(int id, String firstName, String lastName, ILocation location) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.destination = ApplicationLibrary.randomLocation(this.location);
        this.hasService = false;
        this.route = null;
    }

    /* Accessors and Mutators */
    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public boolean hasService() {
        return this.hasService;
    }

    public ILocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(ILocation location) {
        this.location = location;
    }

    @Override
    public void setHasService(boolean hasService) {
        this.hasService = hasService;
    }

    /** Ride request to taxi company from the user */
    @Override
    public void requestService() {
        this.company.provideService(this.id);
    }

    public void requestSharedService() {
        // requests service 50% of the time
        if (ApplicationLibrary.rand() % 2 == 0) {
            this.company.provideSharedService(this.id);
        }
    }

    @Override
    public void cancelRide() {
        if(ApplicationLibrary.rand() % 5 == 0) {
            this.company.cancelService(this.id);
        }
    }

    public void makeReservation() {
        this.company.provideMicroService(this.id);
        Service service1 = new Service(this, this.getLocation(), )
    }

    /**
     * Rate of the service using stars from 1 to 5
     * Randomized rating for half of a users rides.
     *
     * @param service
     */
    @Override
    public void rateService(IService service) {
        // users rate around 50% of the services (1 to 5 stars)
        if (ApplicationLibrary.rand() % 2 == 0) {
            service.setStars(ApplicationLibrary.rand(5) + 1);
        }
    }

    @Override
    public String toString() {
        String s = "";
        if(!this.hasService()) {
            s = " is free with path " + showUserRoute();
        }

        return "User " + this.id + " at " + this.location + " heading to " + this.destination + s;

    } // method toString

    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    @Override
    public String showUserRoute() {
        StringBuilder s = new StringBuilder();

        for (ILocation l : this.route)
            s.append(l.toString()).append(" ");

        return s.toString();
    } // method showDrivingRoute


    public void move() {
        this.location = this.route.get(0);
        this.route.remove(0);

        if (this.route.isEmpty()) {
            if (!hasService()) {
                // the user continues to travel to random locations

                this.destination = ApplicationLibrary.randomLocation(this.location);
                this.route = setRouteToDestination(this.location, this.destination);

            } else {

                IService currentService = this.service;

                // get origin and destination of current service
                ILocation destination = currentService.getPickupLocation();

                // notify when vehicle arrives at pickup or destination
                if (ApplicationLibrary.isSameLocation(this.location,destination)) {

                    notifyArrivalAtPickupLocation();

                }
            }
        }
    } // method move

    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the service
        this.company.arrivedAtPickupLocation();

    } // method notifyArrivalAtPickupLocation

    private List<ILocation> setRouteToDestination(ILocation location, ILocation destination) {
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

} // class User