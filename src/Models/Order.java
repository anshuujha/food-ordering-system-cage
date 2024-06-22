package Models;

import java.util.List;

public class Order {
    private String id;
    private String customerId;
    private String restaurantId;
    private List<FoodItem> foodItems;
    private double totalPrice;
    private String status;


    // Default constructor
    public Order() {
    }

    // Constructor with parameters
    public Order(String id, String customerId, String restaurantId, List<FoodItem> foodItems, double totalPrice, String status) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.foodItems = foodItems;
        this.totalPrice = totalPrice;
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
