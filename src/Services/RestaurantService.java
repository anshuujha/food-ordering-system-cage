package Services;
import Models.Restaurant;
import java.util.List;

public interface RestaurantService {
    void createRestaurant(Restaurant restaurant);
    void updateRestaurant(String id, Restaurant restaurant);
    void deleteRestaurant(String id);
    List<Restaurant> getRestaurantsByOwnerId(String ownerId);
    List<Restaurant> getAllRestaurants();


}

