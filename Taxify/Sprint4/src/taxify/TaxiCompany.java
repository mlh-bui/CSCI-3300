package taxify;

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
        for (int i=0; i<this.users.size(); i++)
            this.users.get(i).setCompany(this);

        for (int i=0; i<this.vehicles.size(); i++)
            this.vehicles.get(i).setCompany(this);
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

        // if there is a free vehicle, assign a random pickup and drop-off location to the new service
        // the distance between the pickup and the drop-off location should be at least 3 blocks

        if (vehicleIndex != -1) {
            ILocation origin, destination;

            do {

                origin = ApplicationLibrary.randomLocation();
                destination = ApplicationLibrary.randomLocation(origin);

            } while (ApplicationLibrary.distance(origin, this.vehicles.get(vehicleIndex).getLocation()) < 3);


            // update the user status

            this.users.get(userIndex).setService(true);

            // create a service with the user, the pickup and the drop-off location

            Service service = new Service(this.users.get(userIndex), origin, destination);

            // NEW CHANGES MADE: AFTER THE REQUEST, IF THE VEHICLE IS IN THE SAME LOCATION AS THE PICK UP LOCATION OF THE USER THEN WE NOTIFY THE COMPANY OF THE ARRIVAL AT PICK UP
            if(service.getPickupLocation() == this.vehicles.get(vehicleIndex).getLocation()) {
                this.arrivedAtPickupLocation(this.vehicles.get(vehicleIndex));
            }

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
     * Notify observer when a vehicle arrives at pick-up location
     *
     * @param vehicle, vehicle in service
     */
    @Override
    public void arrivedAtPickupLocation(IVehicle vehicle) {
        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " loads user " + vehicle.getService().getUser().getId());
    }

    /**
     * Vehicle arrives at drop-off location, notifies the observer
     * Asks user to rate the service
     *
     * @param vehicle, vehicle in service
     */
    @Override
    public void arrivedAtDropoffLocation(IVehicle vehicle) {
        // a vehicle arrives at the drop-off location

        IService service = vehicle.getService();
        int user = service.getUser().getId();
        int userIndex = indexOfUserId(user);

        // the taxi company requests the user to rate the service, and updates its status

        this.users.get(userIndex).rateService(service);
        this.users.get(userIndex).setService(false);

        // update the counter of services

        this.totalServices--;

        notifyObserver(String.format("%-8s",vehicle.getClass().getSimpleName()) + vehicle.getId() + " drops off user " + user);
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

    /**
     * Gets the index of a random free vehicle in vehicle list
     *
     * @return int, free vehicle index or -1 otherwise
     */
    private int findFreeVehicle() {
        if(ApplicationLibrary.rand() % 2 == 0) {
            for(IVehicle v : this.vehicles) {
                if(v.isFree())
                    return this.vehicles.indexOf(v);     // returns the index of the vehicle v in the list
            }
        }
        else {
            for(int i = this.vehicles.size() -1 ; i >=0; i--) {
                if(this.vehicles.get(i).isFree())
                    return i;
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
    }

}