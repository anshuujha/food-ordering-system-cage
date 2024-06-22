package Repositories;
import Models.Restaurant;
import java.util.ArrayList;
import java.util.List;
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private List<Restaurant> restaurants;
    private static RestaurantRepositoryImpl instance;

    private RestaurantRepositoryImpl() {
        restaurants = new ArrayList<>();
    }

    public static RestaurantRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new RestaurantRepositoryImpl();
        }
        return instance;
    }

    @Override
    public Restaurant findByOwnerId(String ownerId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getOwnerId().equals(ownerId)) {
                return restaurant;
            }
        }
        return null;
    }

    public Restaurant findById(String restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(restaurantId)) {
                return restaurant;
            }
        }
        return null;
    }

    public void save(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void update(Restaurant restaurant) {
        int index = -1;
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(restaurant.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            restaurants.set(index, restaurant);
        }
    }

    public void delete(String restaurantId) {
        restaurants.removeIf(restaurant -> restaurant.getId().equals(restaurantId));
    }
    @Override
    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }
    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }
}
