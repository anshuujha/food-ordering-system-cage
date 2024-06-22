package Controllers;
import Models.FoodItem;
import Services.FoodItemServiceImpl;
import java.util.List;

public class FoodItemController {
    private final FoodItemServiceImpl foodItemServiceImpl;
    private static FoodItemController instance;


    public static FoodItemController getInstance() {
        if (instance == null) {
            instance = new FoodItemController();
        }
        return instance;
    }
    public FoodItemController() {
        this.foodItemServiceImpl = FoodItemServiceImpl.getInstance();
    }

    public void addFoodItem(String restaurantId, FoodItem foodItem) {
        foodItemServiceImpl.addFoodItem(restaurantId, foodItem);
    }

    public void updateFoodItem(String foodItemId, FoodItem foodItem) {
        foodItemServiceImpl.updateFoodItem(foodItemId, foodItem);
    }

    public void deleteFoodItem(String foodItemId, String itemId) {
        foodItemServiceImpl.deleteFoodItem(foodItemId);
    }

    public List<FoodItem> getFoodItemsByRestaurantId(String restaurantId) {
        return foodItemServiceImpl.getFoodItemsByRestaurantId(restaurantId);
    }
    public FoodItem getFoodItemByFoodItemId(String foodItemId) {
        return foodItemServiceImpl.getFoodItemById(foodItemId);
    }
}

