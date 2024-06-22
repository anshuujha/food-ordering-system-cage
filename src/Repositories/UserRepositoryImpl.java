package Repositories;
import Models.User;
import java.util.ArrayList;
import java.util.List;
public class UserRepositoryImpl implements UserRepository {
    private List<User> users;
    private static UserRepositoryImpl instance;

    private UserRepositoryImpl() {
        users = new ArrayList<>();
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public User findById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public void save(User user) {
        users.add(user);
    }

    public void update(User user) {
        int index = -1;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            users.set(index, user);
        }
    }

    public void delete(String userId) {
        users.removeIf(user -> user.getId().equals(userId));
    }
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public User getUserProfileById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    @Override
    public void updateUserAddress(String userId, String address) {
        User userToUpdate = findById(userId);
        if (userToUpdate != null) {
            userToUpdate.setAddress(address);
        }
    }

    @Override
    public void updateUserPhoneNumber(String userId, String phoneNumber) {
        User userToUpdate = findById(userId);
        if (userToUpdate != null) {
            userToUpdate.setPhoneNumber(phoneNumber);
        }
    }
}
