// Sprint 5 Project: Taxify
// Marissa Bui - CSCI 3300

import java.util.List;

public class ApplicationSimulator implements IApplicationSimulator, IObserver {
    /** Taxi Company Object */
    private ITaxiCompany company;

    /** List of Users */
    private List<IUser> users;

    /** List of Vehicles */
    private List<IVehicle> vehicles;

    /** Basic Constructor for Application Simulator */
    public ApplicationSimulator(ITaxiCompany company, List<IUser> users, List<IVehicle> vehicles) {
        this.company = company;
        this.users = users;
        this.vehicles = vehicles;
    }

    /**
     * Prints status for vehicles in list
     */
    @Override
    public void show() {

        System.out.println("\n" + this.company.getName() + " status \n");

        // show vehicles
        for (IVehicle vehicle : this.vehicles) {
            if(!(vehicle instanceof MicroVehicle)) {
                System.out.println(vehicle.toString());
            }
        }

        System.out.println();

        // show micro vehicles
        for(IVehicle microVehicle : this.vehicles) {
            if(microVehicle instanceof MicroVehicle) {
                System.out.println(microVehicle.toString());
            }
        }

        System.out.println();

        // show users, note: only show users with routes
        for(IUser user: this.users) {
            if(user.getRoute() != null) {
                System.out.println(user.toString());
            }
        }

        System.out.println();

    } // method show

    /**
     * Prints statistics of the company
     */
    @Override
    public void showStatistics() {
        StringBuilder s = new StringBuilder("\n" + this.company.getName() + " Statistics \n");
        for(IVehicle v: vehicles) {
            s.append("\n");
            s.append(String.format("%-10s", v.getClass().getSimpleName())).append(v.getId()).append(" ");
            s.append(String.format("%5s", v.getStatistics().getServices())).append(" services ");
            s.append(String.format("%5s", v.getStatistics().getDistance())).append(" km. ");
            s.append(String.format("%5s", v.getStatistics().getBilling())).append(" eur. ");
            s.append(String.format("%5s", v.getStatistics().getReviews())).append(" reviews ");
            s.append(String.format("%-5s", v.getStatistics().getStars())).append(" stars");
        }
        System.out.println(s.toString());
    } // method showStatistics

    /**
     * Moves vehicles to the next location
     */
    @Override
    public void update() {
        for (IVehicle vehicle : this.vehicles) {
            vehicle.move();
        }

        for(IUser user : this.users) {
            user.move();
        }
    } // method update

    /** Simulates user request for a service */
    @Override
    public void requestService() {
        int userIndex = findFreeUser();
        if(userIndex != -1) {
            IUser user = users.get(userIndex);

            // request service if the user does not have one yet
            if(user.getService() == null) {
                user.requestService();
            }
        }
    } // method requestService

    /** Simulates user request for a shared service */
    public void requestSharedService() {
        // gets free user
        int userIndex = findFreeUser();
        if(userIndex != -1) {
            IUser user = users.get(userIndex);

            // request service if the user does not have one yet
            if(user.getService() == null) {
                user.requestSharedService();
            }
        }

    } // method requestService

    /** Simulate user request for a micro mobility service */

    public void requestMicroService() {
        int userIndex = findFreeUser();
        if(userIndex != -1) {
            IUser user = users.get(userIndex);

            // request service if the user does not have one yet
            if(user.getService() == null) {
                user.makeReservation();
            }
        }
    } // method requestMicroService

    /** Simulates a user request to a cancel a service */
    public void cancelService() {
        int index = -1;

        for(int i = 0; i < this.vehicles.size(); i++) {
            if(vehicles.get(i).getStatus() == VehicleStatus.PICKUP) {
                index = i;
            }
        }

        if(index != -1) {
            vehicles.get(index).getService().getUser().cancelRide();
        }

    } // method cancelService

    /** Returns total services for company */
    @Override
    public int getTotalServices() {
        return this.company.getTotalServices();
    }

    /** Method to update an observer with a String message */
    @Override
    public void updateObserver(String message) {
        System.out.println(message);
    }

    /**
     * Finds user without a service
     *
     * @return int, index of user
     */
    private int findFreeUser() {
        int index;
        int counter = 0;

        do {
            index = ApplicationLibrary.rand(users.size());
            counter++;
        }
        // continue searching while user at index has a service
        while(users.get(index).hasService() && counter < 50);

        if(counter == 50) {
            index = -1;
        }

        return index; // return a user without a service
    } // method findFreeUser

} // class ApplicationSimulator