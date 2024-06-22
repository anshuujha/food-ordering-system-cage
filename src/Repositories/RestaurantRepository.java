package Repositories;
import Models.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant findByOwnerId(String ownerId);
    void save(Restaurant restaurant);
    Restaurant findById(String restaurantId);
    void update(Restaurant restaurant);
    void delete(String restaurantId);
    List<Restaurant> findAll();
    List<Restaurant> getAllRestaurants();

    }



