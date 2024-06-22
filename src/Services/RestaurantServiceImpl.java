package Services;
import Models.Restaurant;
import Repositories.RestaurantRepository;
import Repositories.RestaurantRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class RestaurantServiceImpl implements RestaurantService {
    private static RestaurantServiceImpl instance;
    private RestaurantRepository restaurantRepository = RestaurantRepositoryImpl.getInstance();

    private RestaurantServiceImpl() {
    }

    public static RestaurantServiceImpl getInstance() {
        if (instance == null) {
            instance = new RestaurantServiceImpl();
        }
        return instance;
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void updateRestaurant(String restaurantId, Restaurant restaurant) {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId);
        if (existingRestaurant != null) {
            restaurant.setId(restaurantId);
            restaurantRepository.update(restaurant);
        }
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    @Override
    public List<Restaurant> getRestaurantsByOwnerId(String ownerId) {
        List<Restaurant> restaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantRepository.findAll()) {
            if (restaurant.getOwnerId().equals(ownerId)) {
                restaurants.add(restaurant);
            }
        }
        return restaurants;
    }
    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }
}


