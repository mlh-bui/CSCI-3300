// Sprint 5 Project: Taxify
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

    /** User service */
    private IService service;

    /** User location */
    private ILocation location;

    /** User destination */
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
        this.route = setRouteToDestination(this.location,this.destination);
    }

    /** Ride request to taxi company from the user */
    @Override
    public void requestService() {
        this.company.provideService(this.id);
        this.route = null;
    } // method requestService

    /** Shared Ride request to taxi company from the user */
    public void requestSharedService() {
        // accepts service 50% of the time
        if (ApplicationLibrary.rand() % 2 == 0) {
            this.company.provideSharedService(this.id);
            this.route = null;
        }
    } // method requestSharedService

    /** Cancel service request to taxi company from the user */
    @Override
    public void cancelRide() {
        if(ApplicationLibrary.rand() % 5 == 0) {
            this.company.cancelService(this.id);
        }
    } // method cancelRide

    /** Reserves a Micro vehicle from the taxi company for the user */
    public void makeReservation() {
        this.company.provideMicroService(this.id);
        if(hasService()) {
            this.destination = this.service.getPickupLocation();
            this.route = setRouteToDestination(this.location, this.destination);
        }
    } // method makeReservation

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
    } // method rateService

    @Override
    public String toString() {
        String s = "";
        if(!hasService()) {
            s = " is heading to " + this.destination + " is free with path " + showUserRoute();
        } else if (hasService() && this.service.getVehicle() instanceof MicroVehicle) {
            s = " booked a micro vehicle service at " + this.destination + " is free with path " + showUserRoute();
        }
        return "User " + this.id + " at " + this.location + s;
    } // method toString

    /**
     * Shows locations on user's route as a string
     *
     * @return route as a string
     */
    @Override
    public String showUserRoute() {
        StringBuilder s = new StringBuilder();

        for (ILocation l : this.route)
            s.append(l.toString()).append(" ");

        return s.toString();
    } // method showDrivingRoute


    /** Simulates a user moving from location to location */
    public void move() {
        if(this.route != null && this.route.size() > 0) {
            this.location = this.route.get(0);
            this.route.remove(0);
        }

        // if the user has not requested a service and their route is empty
        if (hasService() && this.service.getVehicle() instanceof MicroVehicle) {

            // move user to vehicle
            this.route = setRouteToDestination(this.location, this.destination);

            // notify when user arrives at the micro vehicle location
            if (ApplicationLibrary.isSameLocation(this.location, this.destination)) {

                notifyArrivalAtPickupLocation();
                this.destination = this.service.getDropoffLocation();

            }
        } else if (service == null) {
            this.destination = ApplicationLibrary.randomLocation(this.location);
            this.route = setRouteToDestination(this.location, this.destination);
        }

    } // method move

    public void notifyArrivalAtPickupLocation() {
        // notify the company that the vehicle is at the pickup location and start the service
        this.company.userArrivesAtMicroVehicleLocation(this);
        this.route = null;

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

    public ILocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(ILocation location) {
        this.location = location;
    }

    public void setService(IService service) {
        this.service = service;
    } // method setService

    @Override
    public IService getService() {
        return service;
    } // method getService

    public void setRoute(List<ILocation> route) {
        this.route = route;
    } // method setRoute

    @Override
    public List<ILocation> getRoute() {
        return route;
    } // method getRoute

    public boolean hasService() {
        return service != null;
    } // method hasService

    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }
} // class User