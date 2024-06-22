package Services;
import Models.FoodItem;
import Repositories.FoodItemRepository;
import Repositories.FoodItemRepositoryImpl;
import java.util.List;

public class FoodItemServiceImpl implements FoodItemService {
    private static FoodItemServiceImpl instance;
    private FoodItemRepository foodItemRepository = FoodItemRepositoryImpl.getInstance();

    private FoodItemServiceImpl() {}

    public static FoodItemServiceImpl getInstance() {
        if (instance == null) {
            instance = new FoodItemServiceImpl();
        }
        return instance;
    }

    @Override
    public void addFoodItem(String restaurantId, FoodItem foodItem) {
        foodItem.setRestaurantId(restaurantId);
        foodItemRepository.save(foodItem);
    }

    @Override
    public void updateFoodItem(String foodItemId, FoodItem foodItem) {
        FoodItem existingFoodItem = foodItemRepository.getFoodItemById(foodItemId);
        if (existingFoodItem != null) {
            foodItem.setId(foodItemId);
            foodItemRepository.update(foodItem);
        }
    }

    @Override
    public void deleteFoodItem(String foodItemId) {
        foodItemRepository.delete(foodItemId);
    }

    @Override
    public List<FoodItem> getFoodItemsByRestaurantId(String restaurantId) {
        return foodItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public FoodItem getFoodItemById(String foodItemId) {
        return foodItemRepository.getFoodItemById(foodItemId);
    }
}
