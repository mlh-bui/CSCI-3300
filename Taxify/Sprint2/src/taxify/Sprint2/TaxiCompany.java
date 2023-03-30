// V.2 Project: Taxify
// Marissa Bui - CSCI 3300 - 2/17

package taxify1;

import java.util.List;

public class TaxiCompany implements ITaxiCompany {
    private String name;
    private List<IUser> users;
    private List<IVehicle> vehicles;
    private int totalServices;

    public TaxiCompany(String name, List<IUser> users, List<IVehicle> vehicles) {
        this.name = name;
        this.users = users;
        this.vehicles = vehicles;
        this.totalServices = 0;

    }

    @Override
    public int getTotalServices() {
        return this.totalServices;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public boolean requestService(int user) {
        return false;
    }

    public void arrivedAtPickupLocation(IVehicle vehicle) {

    }
    public void arrivedAtDropoffLocation(IVehicle vehicle) {

    }

} // class TaxiCompany