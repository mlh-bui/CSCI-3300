public class MicroVehicle extends Vehicle  {

    private MicroVehicleStatus status;

    public MicroVehicle(int id, ILocation location) {
        super(id,location);
        this.status = MicroVehicleStatus.FREE;
    }


    @Override
    public void move() {
        if(this.isFree()) {
            setRoute(null);
        }

        if (getServices().size() > 0 || getRoute() != null) {
            // get origin and destination of current service
            ILocation origin = getService().getPickupLocation();
            ILocation destination = getService().getDropoffLocation();

            // notify when vehicle arrives at pickup or destination
            if (ApplicationLibrary.isSameLocation(getLocation(), origin)) {

                notifyArrivalAtPickupLocation();

            } else if (ApplicationLibrary.isSameLocation(getLocation(), destination)) {

                notifyArrivalAtDropOffLocation();

            }
        }
    } // method move

    public String toString() {
        String s = " ";

        if(getMicroStatus() == MicroVehicleStatus.BOOKED) {
            s = " booked for user " + this.getService().getUser().getId();
        } else if (getMicroStatus() == MicroVehicleStatus.FREE) {
            s = " free ";
        } else if (getMicroStatus() == MicroVehicleStatus.SERVICE) {
            s = " in service with user " + this.getService().getUser().getId();
        }

        return getId() + " is" + s + "at " + getLocation();
    } // method toString

    public IService getService() {
        if (getMicroStatus() != MicroVehicleStatus.FREE) {
            System.out.println("status from Micro within: " + getMicroStatus());
            return this.getServices().get(this.getServices().size() -1);
        }
        return null;
    }

    public MicroVehicleStatus getMicroStatus() {
        return this.status;
    }

}
