package Repositories;
import Models.FoodItem;
import java.util.List;
public interface FoodItemRepository {
    List<FoodItem> findByRestaurantId(String restaurantId);
    void save(FoodItem foodItem);
    void update(FoodItem foodItem);
    void delete(String foodItemId);
    FoodItem getFoodItemById(String foodItemId);
}


