package v2;

import java.util.ArrayList;
import java.util.List;

public class TaxiCompany implements ITaxiCompany, ISubject {
    /** Company Name */
    private String name;

    /** List of Users */
    private List<IUser> users;

    /** List of Vehicles */
    private List<IVehicle> vehicles;

    /** Services Count */
    private int totalServices;

    /** New Observer for company */
    private IObserver observer;

    /**
     * Basic constructor for TaxiCompany
     * Sets taxi company for list of users and vehicles
     */
    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;
        this.totalServices = 0;

        // set the taxi company for users and vehicles
        for (IUser user : this.users) user.setCompany(this);

        for (IVehicle vehicle : this.vehicles) vehicle.setCompany(this);
    }

    /* Accessor & Mutator Methods */
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getTotalServices() {
        return this.totalServices;
    }

    /**
     * If the vehicle is free, assign a random
     * pick-up and drop-off location to a service
     *
     * @param user, user index
     * @return boolean, true if there is a free vehicle, false if occupied
     */
    @Override
    public boolean provideService(int user) {
        int userIndex = indexOfUserId(user);
        int vehicleIndex = findFreeVehicle();

        if(vehicles.get(vehicleIndex).getService() == null) {
            vehicles.get(vehicleIndex).setService(new ArrayList<>());
        }

        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks

        if (vehicleIndex != 1) {
            ILocation origin, destination;

            do {

                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) < 3);

            // update the user status

            this.users.get(userIndex).setService(true);

            // create a service with the user, the pickup and the drop-off location

            IService service = new Service(this.users.get(userIndex), origin, destination);

            // assign the new service to the vehicle

            this.vehicles.get(vehicleIndex).pickService(service);

            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a service from " + service.toString() + ", the ride is assigned to " +
                    this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                    this.vehicles.get(vehicleIndex).getLocation().toString());

            // update the counter of services

            this.totalServices++;

            return true;
        }

        return false;
    } // method requestService

    @Override
    public boolean provideSharedService(int user) {
        int userIndex = indexOfUserId(user);

        int vehicleIndex = findNearestVehicle(userIndex);
        //System.out.println(vehicleIndex + " FIND"); // for testing, determines whether a vehicle is available for a shared ride or not

        // if a vehicle is close enough to user requesting shared ride
        if (vehicleIndex != -1) {

            ILocation origin = this.users.get(userIndex).getLocation();

            ILocation destination = ApplicationLibrary.randomLocation(); // destination = original destination

            while (origin.getX() == destination.getX()
                    || origin.getY() == destination.getY()
                    || origin.getY() == this.vehicles.get(vehicleIndex).getLocation().getY()
                    || origin.getX() == this.vehicles.get(vehicleIndex).getLocation().getX()) {
                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation();
            }

            //ILocation origin = this.users.get(userIndex).getLocation(); // origin is the user's location

            this.users.get(userIndex).setService(true);     // set the second user's service as true

            Service service = new Service(this.users.get(userIndex), origin, destination);    // create shared service

            service.setShared(true);    // KINDA unnecessary?

            this.vehicles.get(vehicleIndex).pickService(service);

            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a shared service from " + service.toString() + ", the ride is assigned to " +
                    this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                    this.vehicles.get(vehicleIndex).getLocation().toString());

            // update the counter of services

            this.totalServices++;

            return true;
        }


        return false;
    } // method provideSharedService



    /**
     * Notify observer when a vehicle arrives at pick-up location
     *
     * @param vehicle, vehicle in service
     */
    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        notifyObserver(String.format("%-8s", vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads user " + vehicle.getService().getUser().getId());
    }

    // NEWLY ADDED 4/9
    public void arrivedAtSecondaryPickupLocation(IVehicle vehicle) {
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads SECOND user " + vehicle.getService().getUser().getId());
        // notifyObserver(vehicle.getClass().getSimpleName() + " " + vehicle.getId() + " Status: " + vehicle.getStatus());
        // used for testing
    }

    // NEWLY ADDED 4/20
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + vehicle.getService().getUser().getId());
        // notifyObserver(vehicle.getClass().getSimpleName() + " " + vehicle.getId() + " Status: " + vehicle.getStatus());
        // used for testing
    }

    /**
     * Vehicle arrives at drop-off location, notifies the observer
     * Asks user to rate the service
     *
     * @param vehicle, vehicle in service
     */
    @Override
    public void arrivedAtSecondaryDropOffLocation(IVehicle vehicle) {
        // Original code for single user rating

        for(IService service : vehicle.getServices()) {
            int user = service.getUser().getId();
            int userIndex = indexOfUserId(user);

            this.users.get(userIndex).rateService(service);
            this.users.get(userIndex).setService(false);

            // update the counter of services

            this.totalServices--;

            notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);
        }
    } // method arrivedAtDropoffLocation

    /** Method to add an observer */
    @Override
    public void addObserver(IObserver observer) {
        this.observer = observer;
    }

    /** Method to notify an observer with a String message */
    @Override
    public void notifyObserver(String message) {
        this.observer.updateObserver(message);
    }

    public boolean cancelService(int user) {
        int userIndex = indexOfUserId(user);
        IVehicle vehicle = null;

        // find the user's vehicle
        for(IVehicle v: vehicles) {
            if(v.getServices().get(0).getUser() == users.get(userIndex)) {
                vehicle = v;
            }
        }

        vehicle.endService();

        this.users.get(userIndex).setService(false);

        // update the counter of services

        this.totalServices--;

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " is free after user " + user + " cancelled the ride");
        return true;

    } // cancelService

    /**
     * Gets the index of a random free vehicle in vehicle list
     *
     * @return int, free vehicle index or -1 otherwise
     */
    private int findFreeVehicle() {
        // MIGHT CHANGE, Keep for testing
        // Pros: returned free vehicle index
        // Cons: technically not guaranteed to go through every vehicle in the list
        int index;
        int tests = 0;

        do {
            index = ApplicationLibrary.rand(vehicles.size());
            tests++;
        }
        // continue searching for a free vehicle
        while(!vehicles.get(index).isFree() && tests <= vehicles.size() * 5);

        // if no free vehicle is found after 50 tests safe to say none are free
        if(tests == vehicles.size() * 5) {
            return -1;
        }

        return index;

    } // method findFreeVehicle

    /**
     * Gets the index of a random free user in users list
     *
     * @param id, user id
     * @return int, free user index or -1 otherwise
     */
    private int indexOfUserId(int id) {
        for(int i = 0; i < this.users.size(); i++) {
            if(this.users.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    } // method indexOfUserId


    private int findNearestVehicle(int user) { // in relation to user location
        int closest = -1;
        int minDistance = 3;   // minimum distance from user
        int maxDistance = 5;   // Vehicle cannot be more than 3 blocks away
        for(IVehicle v : this.vehicles) {
            int distance = ApplicationLibrary.distance(v.getLocation(), users.get(user).getLocation());
            if(v.getStatus() == VehicleStatus.SERVICE
                    && minDistance < distance && distance < maxDistance) {
                closest = this.vehicles.indexOf(v); // return index of the vehicle
            }
        }

        return closest;
    } // method findNearestVehicle

} // class TaxiCompany