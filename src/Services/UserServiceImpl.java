package Services;
import Models.User;
import Repositories.UserRepository;
import Repositories.UserRepositoryImpl;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;
    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    private UserServiceImpl() {}

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void registerUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserProfileById(String userId) {
        return userRepository.getUserProfileById(userId);
    }
    @Override
    public void updateUserAddress(String userId, String address) {
        userRepository.updateUserAddress(userId, address);
    }
    @Override
    public void updateUserPhoneNumber(String userId, String phoneNumber) {
        userRepository.updateUserPhoneNumber(userId, phoneNumber);
    }
}
