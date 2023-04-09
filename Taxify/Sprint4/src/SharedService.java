package taxify;

import java.util.List;

// NOTE: VERSION 1 where the dropoff location = the same

public class SharedService extends Service {
    /** User object */
    private List<IUser> user;

    /** Pick up location */
    private List<ILocation> pickup;

    /** Drop off location */
    private ILocation dropoff;

    /** Rating of service */
    private List<Integer> stars;


    // THIS IS WRONG NEED TO HAVE: Same dropoff, list of users and pickup locations
    public SharedService(IUser user, ILocation pickup, ILocation dropoff) {
        super(user, pickup, dropoff);
    }
}