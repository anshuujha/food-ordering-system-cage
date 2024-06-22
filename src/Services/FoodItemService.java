package Services;
import Models.FoodItem;
import java.util.List;

public interface FoodItemService {
    void addFoodItem(String restaurantId, FoodItem foodItem);
    void updateFoodItem(String id, FoodItem foodItem);
    void deleteFoodItem(String id);
    FoodItem getFoodItemById(String id);
    List<FoodItem> getFoodItemsByRestaurantId(String restaurantId);
}

