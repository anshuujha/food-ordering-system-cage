package Repositories;

import Models.FoodItem;
import java.util.ArrayList;
import java.util.List;

public class FoodItemRepositoryImpl implements FoodItemRepository {
    private List<FoodItem> foodItems;
    private static FoodItemRepositoryImpl instance;

    private FoodItemRepositoryImpl() {
        foodItems = new ArrayList<>();
    }

    public static FoodItemRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new FoodItemRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<FoodItem> findByRestaurantId(String restaurantId) {
        List<FoodItem> items = new ArrayList<>();
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getRestaurantId().equals(restaurantId)) {
                items.add(foodItem);
            }
        }
        return items;
    }

    public FoodItem getFoodItemById(String foodItemId) {
        for (FoodItem foodItem : foodItems) {
            if (foodItem.getId().equals(foodItemId)) {
                return foodItem;
            }
        }
        return null;
    }

    public void save(FoodItem foodItem) {
        foodItems.add(foodItem);
    }

    public void update(FoodItem foodItem) {
        int index = -1;
        for (int i = 0; i < foodItems.size(); i++) {
            if (foodItems.get(i).getId().equals(foodItem.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            foodItems.set(index, foodItem);
        }
    }

    public void delete(String foodItemId) {
        foodItems.removeIf(foodItem -> foodItem.getId().equals(foodItemId));
    }

}
