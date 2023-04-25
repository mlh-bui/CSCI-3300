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
    private boolean service;

    /** User location to find location relative to a vehicle */
    private ILocation location;

    /** User route to Micro Vehicles */ // COULD BE WRONG???
    private List<ILocation> route;

    /** Basic constructor */
    public User(int id, String firstName, String lastName, ILocation location) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.service = false;
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
        return this.service;
    }

    public ILocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(ILocation location) {
        this.location = location;
    }

    @Override
    public void setService(boolean service) {
        this.service = service;
    }

    /** Ride request to taxi company from the user */
    @Override
    public void requestService() {
        this.company.provideService(this.id);
    }

    public void requestSharedService() {
        // requests service 50% of the time
        if(ApplicationLibrary.rand() % 2 == 0) {
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
        if (ApplicationLibrary.rand() % 2 == 0)
            service.setStars(ApplicationLibrary.rand(5) + 1);
    }

    @Override
    public String toString() {
        return this.getId() + " " + String.format("%-20s",this.getFirstName() + " " + this.getLastName());
    }

    public void setCompany(ITaxiCompany company) {
        this.company = company;
    }

    public List<ILocation> getRoute() {
        return route;
    }

    public void setRoute(List<ILocation> route) {
        this.route = route;
    }


} // class User