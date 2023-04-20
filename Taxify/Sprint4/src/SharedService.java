import java.util.List;

public class SharedService extends Service {
    //private IUser user;
    private int stars;

    /**
     * Basic constructor
     *
     * @param user
     * @param pickup
     * @param dropoff
     */
    public SharedService(IUser user, ILocation pickup, ILocation dropoff) {
        super(user, pickup, dropoff);
        this.stars = 0;
    }

    // THINK THIS IS WRONG
    public boolean acceptService() {
        if(ApplicationLibrary.rand() % 4 == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int getStars() {
        return stars;
    }

    public ILocation getSecondaryPickupLocation() {
        return getPickupLocation();
    }

    @Override
    public void setStars(int stars) {
        this.stars = stars;
    }
}
