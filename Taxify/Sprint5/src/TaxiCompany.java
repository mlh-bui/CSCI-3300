// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300

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
     * @param user, id
     * @return boolean, true if a service can be provided, false if all vehicles are occupied
     */
    @Override
    public boolean provideService(int user) {
        int userIndex = indexOfUserId(user);
        int vehicleIndex = findFreeVehicle(this.users.get(userIndex));


        if (vehicleIndex != -1) {
            ILocation origin = this.users.get(userIndex).getLocation();
            ILocation destination;

            do {
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(destination, this.vehicles.get(vehicleIndex).getLocation()) < 3);

            // create a service with the user, the pickup and the drop-off location

            IService service = new Service(this.users.get(userIndex), origin, destination, this.vehicles.get(vehicleIndex));

            // update the user status

            this.users.get(userIndex).setService(service);

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

    /**
     * If the vehicle is in service and near the user,
     * add a shared service to a different destination
     *
     * @param user, id
     * @return boolean, true if a shared service can be provided, false if no vehicles are found
     */
    @Override
    public boolean provideSharedService(int user) {
        int userIndex = indexOfUserId(user);
        int vehicleIndex = findVehicleForSharedService(userIndex);

        // if a vehicle is close enough to user requesting shared ride
        if (vehicleIndex != -1) {

            ILocation currentLocation = this.vehicles.get(vehicleIndex).getLocation();
            ILocation origin = this.users.get(userIndex).getLocation();
            ILocation destination = ApplicationLibrary.randomLocation(origin); // destination = original destination

            Service service = new Service(this.users.get(userIndex), origin, destination, this.vehicles.get(vehicleIndex));    // create shared service

            // update the user status

            this.users.get(userIndex).setService(service);

            // assign the new service to the vehicle

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
     * The closest micro vehicle is assigned a user and a service
     *
     * @param user, id
     * @return
     */
    @Override
    public boolean provideMicroService(int user) {
        int userIndex = indexOfUserId(user);
        int vehicleIndex = findNearestMicroVehicle(userIndex);

        if (vehicleIndex != -1) {

            ILocation origin = this.vehicles.get(vehicleIndex).getLocation();
            ILocation destination = ApplicationLibrary.randomLocation(origin);

            Service service = new Service(this.users.get(userIndex), this.vehicles.get(vehicleIndex).getLocation(), destination, this.vehicles.get(vehicleIndex));

            this.users.get(userIndex).setService(service);
            this.vehicles.get(vehicleIndex).pickService(service);

            notifyObserver("User " + this.users.get(userIndex).getId() + " requests a Micro-mobility service from " + service.toString() + ", the user reserves " +
                    this.vehicles.get(vehicleIndex).getClass().getSimpleName() + " " + this.vehicles.get(vehicleIndex).getId() + " at location " +
                    this.vehicles.get(vehicleIndex).getLocation().toString());

            // update the counter of services

            this.totalServices++;

            return true;
        }

        return false;
    } // method provideMicroService

    /** Notify observer when company is notified of user's arrival at the Mico vehicle */
    @Override
    public void userArrivesAtMicroVehicleLocation(IUser user) {
        notifyObserver(String.format("%-8s", user.getClass().getSimpleName() + " " + user.getId() + " arrives at " + user.getService().getVehicle().getClass().getSimpleName().toString()));
    }

    /** Notify observer when a vehicle arrives at pick-up location */
    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        notifyObserver(String.format("%-8s", vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads user " + vehicle.getService().getUser().getId());
    }

    /** Notify observer when a vehicle arrive at second user's pick up location */
    public void arrivedAtSecondaryPickupLocation(IVehicle vehicle) {
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads second user " + vehicle.getService().getUser().getId());
    }

    /**
     * Vehicle arrives at drop-off location, notifies the observer
     * Asks user to rate the service
     */
    @Override
    public void arrivedAtDropOffLocation(IVehicle vehicle) {

        // All services in the vehicle rate the service and end service
        int user = vehicle.getService().getUser().getId();
        int userIndex = indexOfUserId(user);


        this.users.get(userIndex).rateService(vehicle.getService());

        // update the counter of services

        this.totalServices--;

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);

    } // method arrivedAtDropOffLocation

    @Override
    public void arrivedAtSecondaryDropOffLocation(IVehicle vehicle) {

        // All users in the vehicle rate the service and end service
        int user = vehicle.getService().getUser().getId();
        int userIndex = indexOfUserId(user);

        this.users.get(userIndex).rateService(vehicle.getService());

        // update the counter of services

        this.totalServices--;

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off SECOND user " + user);
    }

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

        // find the user's vehicle
        for(IVehicle v: vehicles) {
            // if the vehicle has a service & it's user matches the user asking for a cancellation
            if(v.getService() != null && users.get(userIndex) == v.getService().getUser()) {

                v.endService();

                this.users.get(userIndex).setService(null);

                // update the counter of services

                this.totalServices--;

                notifyObserver(String.format("%-8s",v.getClass().getSimpleName()) + v.getId() + " is free after user " + user + " cancelled the ride");

                return true;
            }
        }
        return false;

    } // cancelService

    /**
     * Gets the index of a random free vehicle in vehicle list
     *
     * @return int, free vehicle index or -1 otherwise
     */
    private int findFreeVehicle(IUser user) {

        if(ApplicationLibrary.rand() % 2 == 0) {
            for(IVehicle v : this.vehicles) {
                boolean sameLocation = ApplicationLibrary.isSameLocation(v.getLocation(), user.getLocation());
                if (v.isFree()
                        && !(v instanceof MicroVehicle)
                        && !sameLocation) {
                    return this.vehicles.indexOf(v);     // returns the index of the vehicle v in the list
                }
            }
        } else {
            for(int i = this.vehicles.size() -1 ; i >=0; i--) {
                boolean sameLocation = ApplicationLibrary.isSameLocation(vehicles.get(i).getLocation(), user.getLocation());

                if (this.vehicles.get(i).isFree()
                        && !(this.vehicles.get(i) instanceof MicroVehicle)
                        && !sameLocation) {
                    return i;
                }
            }
        }
        return -1;

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

    /**
     * Finds vehicle within the minimum and maximum distance
     *
     * @param userIndex, user from List
     * @return index of a close Vehicle, -1 if otherwise
     */
    private int findVehicleForSharedService(int userIndex) { // in relation to user location

        int minDistance = 2;   // minimum distance from user
        int maxDistance = 5;   // Vehicle cannot be more than 3 blocks away

        for(IVehicle v : this.vehicles) {
            int distance = ApplicationLibrary.distance(v.getLocation(), users.get(userIndex).getLocation());

            if(v.getStatus() == VehicleStatus.SERVICE
                    && minDistance < distance && distance < maxDistance
                    && !(v instanceof MicroVehicle)) {
                return this.vehicles.indexOf(v); // return index of the vehicle
            }
        }

        return -1;

    } // method findVehicleForSharedService

    /**
     * Finds the closest micro vehicle relative to the user's location
     *
     * @param userIndex, user from List
     * @return index of the closest micro vehicle, -1 if otherwise
     */
    private int findNearestMicroVehicle(int userIndex) {
        int closest = -1; // returns -1 if no vehicles are found
        int minDistance = 10;
        for(IVehicle v : this.vehicles) {
            int distance = ApplicationLibrary.distance(v.getLocation(), users.get(userIndex).getLocation());

            if(v instanceof MicroVehicle && v.isFree()
                    && distance > 1 && distance < minDistance) {
                minDistance = distance;
                closest = this.vehicles.indexOf(v); // return index of the vehicle
            }
        }

        return closest;
    } // method findNearestMicroVehicle

} // class TaxiCompany