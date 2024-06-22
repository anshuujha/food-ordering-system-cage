package Services;
import Models.User;

public interface UserService {
    void registerUser(User user);
    User loginUser(String username, String password);
    User getUserProfileById(String userId);
    void updateUserAddress(String userId, String address);
    void updateUserPhoneNumber(String userId, String phoneNumber);
}

