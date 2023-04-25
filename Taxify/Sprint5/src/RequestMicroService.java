public class RequestMicroService implements IRequestService {

    @Override
    public void requestService(IUser user) {
        if(!user.hasService()) {
            user.makeReservation();
        }
    }
}
