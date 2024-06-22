package Controllers;
import Models.User;
import Services.UserServiceImpl;
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private static UserController instance;

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }
    public UserController() {
        this.userServiceImpl = UserServiceImpl.getInstance();
    }

    public void registerUser(User user) {
        userServiceImpl.registerUser(user);
    }

    public User loginUser(String username, String password) {
        return userServiceImpl.loginUser(username, password);
    }

    public User getUserProfileById(String userId) {
        return userServiceImpl.getUserProfileById(userId);
    }
    public void updateUserAddress(String userId, String address) {
        userServiceImpl.updateUserAddress(userId, address);
    }

    public void updateUserPhoneNumber(String userId, String phoneNumber) {
        userServiceImpl.updateUserPhoneNumber(userId, phoneNumber);
    }
}

