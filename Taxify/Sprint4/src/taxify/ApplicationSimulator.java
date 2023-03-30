package taxify;

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

        for (int i=0; i<this.vehicles.size(); i++) {
            System.out.println(this.vehicles.get(i).toString());
        }
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
    } // method update

    /** Simulates user request for a service */
    @Override
    public void requestService() {
        // finds a "free" user and requests a service to the Taxi Company
        int index;

        do {
            index = ApplicationLibrary.rand(users.size());
        }
        // continue searching while user at index has a service
        while(users.get(index).getService());

        // request service if the user does not have one yet
        if(!users.get(index).getService()) {
            users.get(index).requestService();
        }

    } // method requestService


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
}
