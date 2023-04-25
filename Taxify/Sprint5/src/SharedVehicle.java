public class SharedVehicle  {
    /**
     * Basic Constructor for Vehicle
     *
     * @param id
     * @param location
    public SharedVehicle(int id, ILocation location) {
        super(id, location);
    }

    public void startSharedService() {
        this.destination = this.service.get(0).getDropoffLocation();
        this.route = setDrivingRouteToDestination(this.location, this.destination);
        this.status = VehicleStatus.SHARED_SERVICE;
    } // method startService

    @Override
    public void notifyArrivalAtSecondaryPickUpLocation() {
        this.company.arrivedAtSecondaryPickupLocation(this);
        this.startSharedService();
    }

    @Override
    public void notifyArrivalAtSecondaryDropOffLocation() {
        this.company.arrivedAtSecondaryDropOffLocation(this);
        endService();
    }

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
            else {
                IService currentService = this.getService();

                // get origin and destination of current service
                ILocation origin = this.service.get(0).getPickupLocation();
                ILocation destination = this.service.get(0).getDropoffLocation();
                ILocation secondPickUp = currentService.getPickupLocation();
                ILocation secondDropOff = currentService.getDropoffLocation();

                // notify when vehicle arrives at secondary pickup (current service pickup location)
                if (ApplicationLibrary.isSameLocation(this.location, secondPickUp)) {

                    notifyArrivalAtSecondaryPickUpLocation();

                } else if(ApplicationLibrary.isSameLocation(this.location, secondDropOff)) {

                    notifyArrivalAtSecondaryDropOffLocation();

                } else if (ApplicationLibrary.isSameLocation(this.location,origin)) {

                        notifyArrivalAtPickupLocation();

                    } else if (ApplicationLibrary.isSameLocation(this.location,destination)) {

                        notifyArrivalAtDropOffLocation();

                    }
            }

        }
    } // method move

    @Override
    public String toString() {
        String s = "";

        if (this.v == VehicleStatus.SHARED_SERVICE) {
            s = " in shared service";
        }
        return super.toString() + s;

    } // method toString
*/
}
