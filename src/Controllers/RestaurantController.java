package Controllers;
import Models.Restaurant;

import Services.RestaurantServiceImpl;
import java.util.List;
public class RestaurantController {
    private final RestaurantServiceImpl restaurantServiceImpl;
    private static RestaurantController instance;


    public static RestaurantController getInstance() {
        if (instance == null) {
            instance = new RestaurantController();
        }
        return instance;
    }
    public RestaurantController() {
        this.restaurantServiceImpl = RestaurantServiceImpl.getInstance();
    }

    public void createRestaurant(Restaurant restaurant) {
        restaurantServiceImpl.createRestaurant(restaurant);
    }

    public void updateRestaurant(String restaurantId, Restaurant restaurant) {
        restaurantServiceImpl.updateRestaurant(restaurantId, restaurant);
    }

    public void deleteRestaurant(String restaurantId) {
        restaurantServiceImpl.deleteRestaurant(restaurantId);
    }

    public List<Restaurant> getRestaurantByOwnerId(String ownerId) {
        return restaurantServiceImpl.getRestaurantsByOwnerId(ownerId);
    }
    public List<Restaurant> getAllRestaurants() {
        return restaurantServiceImpl.getAllRestaurants();
    }
}
