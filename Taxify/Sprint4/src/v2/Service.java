// Sprint 4 Project: Taxify
// Marissa Bui - CSCI 3300

package v2;

public class Service implements IService {
    /** User object */
    private IUser user;

    /** Pick up location */
    private ILocation pickup;

    /** Drop off location */
    private ILocation dropoff;

    /** Rating of service */
    private int stars;

    /** Basic constructor */
    public Service(IUser user, ILocation pickup, ILocation dropoff) {
        this.user = user;
        this.pickup = pickup;
        this.dropoff = dropoff;
        this.stars = 0;
    }

    /* Accessors & Mutators */
    @Override
    public IUser getUser() {
        return this.user;
    }

    @Override
    public ILocation getPickupLocation() {
        return this.pickup;
    }

    @Override
    public ILocation getDropoffLocation() {
        return this.dropoff;
    }

    @Override
    public int getStars() {
        return this.stars;
    }

    @Override
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * Finds the distance between pick up & drop off
     *
     * @return int of distance travelled
     */
    @Override
    public int calculateDistance() {
        return Math.abs(this.pickup.getX() - this.dropoff.getX()) + Math.abs(this.pickup.getY() - this.dropoff.getY());
    }

    @Override
    public String toString() {
        return this.getPickupLocation().toString() + " to " + this.getDropoffLocation().toString();
    }

} // class Service