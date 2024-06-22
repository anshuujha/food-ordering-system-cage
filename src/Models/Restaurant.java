package Models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String id;
    private String ownerId;
    private String name;
    private String address;
    private String phone;
    private List<FoodItem> foodItems = new ArrayList<>();
    public Restaurant(){

    }
    // Constructor with parameters
    public Restaurant(String id, String ownerId, String name, String address, String phone) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }
}
