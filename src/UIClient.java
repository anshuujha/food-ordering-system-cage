import Controllers.FoodItemController;
import Controllers.OrderController;
import Controllers.RestaurantController;
import Controllers.UserController;
import Models.FoodItem;
import Models.Order;
import Models.Restaurant;
import Models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UIClient {
    private static UIClient instance;
    private static Scanner scanner = new Scanner(System.in);
    private static UserController userController = UserController.getInstance();
    private static RestaurantController restaurantController = RestaurantController.getInstance();
    private static FoodItemController foodItemController = FoodItemController.getInstance();
    private static OrderController orderController = OrderController.getInstance();

    private UIClient() {}

    public static UIClient getInstance() {
        if (instance == null) {
            instance = new UIClient();
        }
        return instance;
    }


    public void Start() {
        initializeDemoData(); //I'm calling the hardcoded data so that it becomes available to access for testing
        System.out.println("Welcome to the Food Ordering System");

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void register() {
        System.out.println("Register as:");
        System.out.println("1. Customer");
        System.out.println("2. Restaurant Owner");
        System.out.print("Select an option: ");
        int roleOption = scanner.nextInt();
        scanner.nextLine();

        String userId;
        while (true) {
            System.out.print("Enter 6-digit User ID: ");
            userId = scanner.nextLine();

            if (userId.matches("\\d{6}")) {
                break;
            } else {
                System.out.println("User ID must be a 6-digit number. Please try again.");
            }
        }

        String username;
        while (true) {
            System.out.print("Enter username (7-20 characters): ");
            username = scanner.nextLine();

            if (username.length() >= 7 && username.length() <= 20) {
                break;
            } else {
                System.out.println("Username must be between 7 and 20 characters.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$")) {
                break;
            } else {
                System.out.println("Password must contain at least one uppercase letter, one lowercase letter, and one digit. Please try again.");
            }
        }

        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();

            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        String role = (roleOption == 1) ? "customer" : "owner";

        String address;
        while (true) {
            System.out.print("Enter address: ");
            address = scanner.nextLine();

            if (address.length() >= 5 && address.length() <= 50) {
                break;
            } else {
                System.out.println("Address must be between 5 and 50 characters.");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("Enter phone number (10 digits): ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Phone number must be a 10-digit number. Please try again.");
            }
        }

        User user = new User();
        user.setId(userId);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRole(role);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);

        userController.registerUser(user);

        System.out.println("Registration successful.");
    }

    private static boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = userController.loginUser(username, password);
        if (user != null) {
            System.out.println("Login successful.");
            if (user.getRole().equals("customer")) {
                customerOptions(user);
            } else {
                ownerOptions(user);
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void createRestaurant(User user) {
        System.out.print("Enter Restaurant ID: ");
        String restaurantId = scanner.nextLine();

        while (restaurantId.length() != 6 || !restaurantId.matches("\\d{6}")) {
            System.out.println("Restaurant ID must be a six-digit number.");
            System.out.print("Enter Restaurant ID: ");
            restaurantId = scanner.nextLine();
        }

        System.out.print("Enter Restaurant Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Restaurant Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Restaurant Phone Number: ");
        String phoneNumber = scanner.nextLine();

        while (phoneNumber.length() != 10 || !phoneNumber.matches("\\d{10}")) {
            System.out.println("Phone number must be a 10-digit number.");
            System.out.print("Enter Restaurant Phone Number: ");
            phoneNumber = scanner.nextLine();
        }

        Restaurant restaurant = new Restaurant(restaurantId, user.getId(), name, address, phoneNumber);
        restaurantController.createRestaurant(restaurant);
        System.out.println("Restaurant created successfully.");
    }

    private static void customerOptions(User user) {
        while (true) {
            System.out.println("Customer Options:");
            System.out.println("1. Place Order");
            System.out.println("2. View Your Orders");
            System.out.println("3. Update Address");
            System.out.println("4. Update Phone Number");
            System.out.println("5. View Profile");
            System.out.println("6. Exit");

            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    placeOrder(user);
                    break;
                case 2:
                    viewCustomerOrders(user);
                    break;
                case 3:
                    updateAddress(user);
                    break;
                case 4:
                    updatePhoneNumber(user);
                    break;
                case 5:
                    viewProfile(user);
                    break;
                case 6:
                    System.out.println("Exit...");
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void placeOrder(User user) {
        List<Restaurant> restaurants = restaurantController.getAllRestaurants();
        System.out.println("Available Restaurants:");
        int count = 1;
        for (Restaurant restaurant : restaurants) {
            System.out.println(count + ". " + restaurant.getName());
            count++;
        }
        System.out.print("Select a restaurant: ");
        int restaurantOption = scanner.nextInt();
        scanner.nextLine();

        if (restaurantOption < 1 || restaurantOption > restaurants.size()) {
            System.out.println("Invalid restaurant selection.");
            return;
        }
        Restaurant selectedRestaurant = restaurants.get(restaurantOption - 1);
        List<FoodItem> foodItems = foodItemController.getFoodItemsByRestaurantId(selectedRestaurant.getId());
        System.out.println("Available Food Items at " + selectedRestaurant.getName() + ":");
        for (int i = 0; i < foodItems.size(); i++) {
            FoodItem item = foodItems.get(i);
            System.out.println((i + 1) + ". " + item.getName() + " - ₹" + item.getPrice());
        }

        List<FoodItem> orderedItems = new ArrayList<>();
        double totalPrice = 0;
        while (true) {
            System.out.print("Select an item to add to your order (Enter 0 to finish): ");
            int itemOption = scanner.nextInt();
            scanner.nextLine();

            if (itemOption == 0) {
                break;
            } else if (itemOption > 0 && itemOption <= foodItems.size()) {
                FoodItem selectedItem = foodItems.get(itemOption - 1);
                orderedItems.add(selectedItem);
                totalPrice += selectedItem.getPrice();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        if (!orderedItems.isEmpty()) {
            Order order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setCustomerId(user.getId());
            order.setRestaurantId(selectedRestaurant.getId());
            order.setStatus("Placed");
            order.setFoodItems(orderedItems);
            order.setTotalPrice(totalPrice);

            orderController.placeOrder(order);
            System.out.println("Total Price: ₹" + totalPrice);
            System.out.println("Your food order has been placed. It will arrive hot at your address => " + user.getAddress() + " very soon.");
            System.out.println("Thank you for choosing us ! Have a great meal.");

        } else {
            System.out.println("No items selected. Order cancelled.");
        }

    }

    private static void viewCustomerOrders(User user) {
        List<Order> orders = orderController.getOrdersByCustomerId(user.getId());
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Your Orders:");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getId() + ", Total Price: ₹" + order.getTotalPrice() + ", Status: " + order.getStatus());
                System.out.println("Food Items:");
                for (FoodItem item : order.getFoodItems()) {
                    System.out.println("- " + item.getName() + ": $" + item.getPrice());
                }
                System.out.println();
            }
        }
    }
    private static void updateAddress(User user) {
        System.out.print("Enter new address: ");
        String newAddress = scanner.nextLine();
        userController.updateUserAddress(user.getId(), newAddress);
    }

    private static void updatePhoneNumber(User user) {
        String newPhoneNumber;
        while (true) {
            System.out.print("Enter new 10-digit phone number: ");
            newPhoneNumber = scanner.nextLine();

            if (newPhoneNumber.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Phone number must be exactly 10 digits. Please try again.");
            }
        }
        userController.updateUserPhoneNumber(user.getId(), newPhoneNumber);
    }
    private static void viewProfile(User user) {
        User profile = userController.getUserProfileById(user.getId());
        if (profile != null) {
            System.out.println("User Profile:");
            System.out.println(profile);
        } else {
            System.out.println("User not found.");
        }
    }


    private static void ownerOptions(User user) {
        while (true) {
            System.out.println("Owner Options:");
            System.out.println("1. Add Food Item");
            System.out.println("2. Update Food Item");
            System.out.println("3. Delete Food Item");
            System.out.println("4. View Orders");
            System.out.println("5. Delete Restaurant");
            System.out.println("6. Update Restaurant");
            System.out.println("7. Create Restaurant");
            System.out.println("8. Update Order Status");
            System.out.println("9. Exit");

            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addFoodItem(user);
                    break;
                case 2:
                    updateFoodItem(user);
                    break;
                case 3:
                    deleteFoodItem(user);
                    break;
                case 4:
                    viewRestaurantOrders(user);
                    break;
                case 5:
                    deleteRestaurant(user);
                    break;
                case 6:
                    updateRestaurant(user);
                    break;
                case 7:
                    createRestaurant(user);
                    break;
                case 8:
                    updateOrderStatus(user);
                    break;
                case 9:
                    System.exit(0);
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void addFoodItem(User user) {
        if (!user.getRole().equals("owner")) {
            System.out.println("Only restaurant owners can add food items.");
            return;
        }
        System.out.print("Enter Restaurant ID: ");
        String restaurantId = scanner.nextLine();

        System.out.print("Enter Food Item ID: ");
        String foodItemId = scanner.nextLine();

        System.out.print("Enter Food Item Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Food Item Description: ");
        String description = scanner.nextLine();

        double price;
        while (true) {
            System.out.print("Enter Food Item Price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        FoodItem foodItem = new FoodItem();
        foodItem.setRestaurantId(restaurantId);
        foodItem.setId(foodItemId);
        foodItem.setName(name);
        foodItem.setDescription(description);
        foodItem.setPrice(price);
        foodItem.setAvailability(true);

        foodItemController.addFoodItem(restaurantId, foodItem);
        System.out.println("Food item added successfully.");
    }


    private static void updateFoodItem(User user) {
        if (!user.getRole().equals("owner")) {
            System.out.println("Only restaurant owners can update food items.");
            return;
        }
        System.out.print("Enter Restaurant ID: ");
        String restaurantId = scanner.nextLine();

        System.out.print("Enter Food Item ID to update: ");
        String foodItemId = scanner.nextLine();

        System.out.print("Enter new Food Item Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Food Item Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter new Food Item Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        FoodItem foodItem = new FoodItem();
        foodItem.setRestaurantId(restaurantId);
        foodItem.setId(foodItemId);
        foodItem.setName(name);
        foodItem.setDescription(description);
        foodItem.setPrice(price);
        foodItem.setAvailability(true);

        foodItemController.updateFoodItem(restaurantId, foodItem);
        System.out.println("Food item updated successfully.");
    }

    private static void deleteFoodItem(User user) {
        if (!user.getRole().equals("owner")) {
            System.out.println("Only restaurant owners can update food items.");
            return;
        }
        System.out.print("Enter Restaurant ID: ");
        String restaurantId = scanner.nextLine();

        System.out.print("Enter Food Item ID to delete: ");
        String foodItemId = scanner.nextLine();

        foodItemController.deleteFoodItem(restaurantId, foodItemId);
        System.out.println("Food item deleted successfully.");
    }

    private static void viewRestaurantOrders(User user) {
        List<Restaurant> ownerRestaurants = restaurantController.getRestaurantByOwnerId(user.getId());
        if (ownerRestaurants.isEmpty()) {
            System.out.println("You have no registered restaurants.");
            return;
        }

        System.out.println("Your Restaurants:");
        int count = 1;
        for (Restaurant restaurant : ownerRestaurants) {
            System.out.println(count + ". " + restaurant.getName());
            count++;
        }
        System.out.print("Select a restaurant to view orders: ");
        int restaurantOption = scanner.nextInt();
        scanner.nextLine();

        if (restaurantOption < 1 || restaurantOption > ownerRestaurants.size()) {
            System.out.println("Invalid restaurant selection.");
            return;
        }

        Restaurant selectedRestaurant = ownerRestaurants.get(restaurantOption - 1);
        List<Order> orders = orderController.getOrdersByRestaurantId(selectedRestaurant.getId());

        if (orders.isEmpty()) {
            System.out.println("No orders for this restaurant.");
            return;
        }

        System.out.println("Orders for " + selectedRestaurant.getName() + ":");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId());
            System.out.println("Customer: " + userController.getUserProfileById(order.getCustomerId()).getUsername());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Total Price: ₹" + order.getTotalPrice());
            System.out.println("Food Items:");
            for (FoodItem item : order.getFoodItems()) {
                System.out.println("- " + item.getName() + " - ₹" + item.getPrice());
            }
            System.out.println();
        }
    }



    private static void deleteRestaurant(User user) {
        if (!user.getRole().equals("owner")) {
            System.out.println("Only restaurant owners can update food items.");
            return;
        }
        System.out.print("Enter Restaurant ID to delete: ");
        String restaurantId = scanner.nextLine();

        restaurantController.deleteRestaurant(restaurantId);
        System.out.println("Restaurant deleted successfully.");
    }
    private static void updateRestaurant(User user) {
        System.out.print("Enter Restaurant ID to update: ");
        String restaurantId = scanner.nextLine();
        if (!restaurantId.matches("\\d{6}")) {
            System.out.println("Restaurant ID must be a 6-digit number.");
            return;
        }

        System.out.print("Enter new Restaurant Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new Restaurant Address: ");
        String address = scanner.nextLine();

        String phoneNumber;
        while (true) {
            System.out.print("Enter 10-digit Phone Number: ");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Phone number must be exactly 10 digits. Please try again.");
            }
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantId);
        restaurant.setOwnerId(user.getId());
        restaurant.setName(name);
        restaurant.setAddress(address);
        restaurant.setPhone(phoneNumber);

        restaurantController.updateRestaurant(restaurantId, restaurant);
        System.out.println("Restaurant updated successfully.");
    }

    private static void updateOrderStatus(User user) {
        List<Restaurant> ownerRestaurants = restaurantController.getRestaurantByOwnerId(user.getId());
        if (ownerRestaurants.isEmpty()) {
            System.out.println("You have no registered restaurants.");
            return;
        }

        System.out.println("Your Restaurants:");
        int count = 1;
        for (Restaurant restaurant : ownerRestaurants) {
            System.out.println(count + ". " + restaurant.getName());
            count++;
        }
        System.out.print("Select a restaurant to view orders: ");
        int restaurantOption = scanner.nextInt();
        scanner.nextLine();

        if (restaurantOption < 1 || restaurantOption > ownerRestaurants.size()) {
            System.out.println("Invalid restaurant selection.");
            return;
        }

        Restaurant selectedRestaurant = ownerRestaurants.get(restaurantOption - 1);
        List<Order> orders = orderController.getOrdersByRestaurantId(selectedRestaurant.getId());

        if (orders.isEmpty()) {
            System.out.println("No orders for this restaurant.");
            return;
        }

        System.out.println("Orders for " + selectedRestaurant.getName() + ":");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println((i + 1) + ". Order ID: " + order.getId() + ", Status: " + order.getStatus());
        }
        System.out.print("Select an order to update status: ");
        int orderOption = scanner.nextInt();
        scanner.nextLine();

        if (orderOption < 1 || orderOption > orders.size()) {
            System.out.println("Invalid order selection.");
            return;
        }

        Order selectedOrder = orders.get(orderOption - 1);
        System.out.println("Current status: " + selectedOrder.getStatus());
        System.out.print("Enter new status: ");
        String newStatus = scanner.nextLine();

        orderController.updateOrderStatus(selectedOrder.getId(), newStatus);
        System.out.println("Order status updated successfully.");
    }
    private static void initializeDemoData() {
        userController.registerUser(new User("254689", "anshujha", "Anshu01", "anshu@gmail.com", "customer", "123 Main St", "9876543210"));
        userController.registerUser(new User("456852", "amanjha", "Anshu01", "aman@gmail.com", "customer", "456 Heaven St", "9998887776"));
        userController.registerUser(new User("457851", "aruntiwary", "Anshu01", "arun@gmail.com", "customer", "789 Exhibition St", "1112223334"));
        userController.registerUser(new User("326448", "rahulsingh", "Anshu01", "rahul@gmail.com", "customer", "321 Patna St", "7776665555"));
        userController.registerUser(new User("215547", "kailash", "Anshu01", "kailash@gmail.com", "owner", "456 Patna St", "8889990000"));
        userController.registerUser(new User("324876", "raghunath", "Anshu01", "raghunath@gmail.com", "owner", "789 Gaya St", "3332221111"));

        // Adding restaurants
        restaurantController.createRestaurant(new Restaurant("123456", "123456", "Pizza Palace", "123 Main St", "1234567890"));
        restaurantController.createRestaurant(new Restaurant("234567", "234567", "Burger Barn", "456 Heaven St", "9876543210"));
        restaurantController.createRestaurant(new Restaurant("345678", "345678", "Patna Spices", "789 Exhibition St", "5555555555"));
        restaurantController.createRestaurant(new Restaurant("456789", "456789", "Sushi Spot", "321 Patna St", "1112223333"));

        // Adding food items
        foodItemController.addFoodItem("123456", new FoodItem("123456", "100001", "Cheese Pizza", "Delicious cheese pizza", 8.99, true));
        foodItemController.addFoodItem("123456", new FoodItem("123456", "100002", "Pepperoni Pizza", "Tasty pepperoni pizza", 9.99, true));
        foodItemController.addFoodItem("123456", new FoodItem("123456", "100003", "Veggie Pizza", "Healthy veggie pizza", 7.99, true));
        foodItemController.addFoodItem("123456", new FoodItem("123456", "100004", "Meat Lovers Pizza", "Loaded with meat", 11.99, true));
        foodItemController.addFoodItem("123456", new FoodItem("123456", "100005", "BBQ Chicken Pizza", "Savory BBQ chicken pizza", 10.99, true));

        foodItemController.addFoodItem("234567", new FoodItem("234567", "200001", "Classic Burger", "Juicy classic burger", 5.99, true));
        foodItemController.addFoodItem("234567", new FoodItem("234567", "200002", "Cheese Burger", "Burger with cheese", 6.49, true));
        foodItemController.addFoodItem("234567", new FoodItem("234567", "200003", "Egg Burger", "Burger with bacon", 6.99, true));
        foodItemController.addFoodItem("234567", new FoodItem("234567", "200004", "Veggie Burger", "Healthy veggie burger", 5.49, true));
        foodItemController.addFoodItem("234567", new FoodItem("234567", "200005", "Double Burger", "Double patty burger", 7.99, true));

        foodItemController.addFoodItem("345678", new FoodItem("345678", "300001", "Chicken Taco", "Taco with chicken", 2.99, true));
        foodItemController.addFoodItem("345678", new FoodItem("345678", "300002", "Meat Taco", "Taco with Meat", 3.49, true));
        foodItemController.addFoodItem("345678", new FoodItem("345678", "300003", "Fish Taco", "Taco with fish", 3.99, true));
        foodItemController.addFoodItem("345678", new FoodItem("345678", "300004", "Veggie Taco", "Taco with veggies", 2.49, true));
        foodItemController.addFoodItem("345678", new FoodItem("345678", "300005", "Shrimp Taco", "Taco with shrimp", 4.49, true));

        foodItemController.addFoodItem("456789", new FoodItem("456789", "400001", "Sushi Roll", "Delicious sushi roll", 9.99, true));
        foodItemController.addFoodItem("456789", new FoodItem("456789", "400002", "Sashimi", "Fresh sashimi", 12.99, true));
        foodItemController.addFoodItem("456789", new FoodItem("456789", "400003", "Tempura", "Crispy tempura", 8.99, true));
        foodItemController.addFoodItem("456789", new FoodItem("456789", "400004", "California Roll", "Classic California roll", 10.99, true));
        foodItemController.addFoodItem("456789", new FoodItem("456789", "400005", "Dragon Roll", "Exquisite dragon roll", 14.99, true));

    }

}
