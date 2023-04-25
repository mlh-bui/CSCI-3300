public class RequestService implements IRequestService {
    @Override
    public void requestService(IUser user) {
        if(!user.hasService()) {
            user.requestService();
        }
    }
}
