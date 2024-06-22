package Repositories;
import Models.User;

public interface UserRepository {
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(String userId);
    void save(User user);
    User findByUsernameAndPassword(String username, String password);
    User getUserProfileById(String userId);
    void updateUserAddress(String userId, String address);
    void updateUserPhoneNumber(String userId, String phoneNumber);
}


