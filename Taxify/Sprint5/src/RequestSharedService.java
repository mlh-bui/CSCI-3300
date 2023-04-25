public class RequestSharedService implements IRequestService {
    @Override
    public void requestService(IUser user) {
        if(!user.hasService()) {
            user.requestSharedService();
        }
    }
}
