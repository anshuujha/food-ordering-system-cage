# food-ordering-system-cage
A plain java based food ordering application 
Food Ordering System
Introduction
The Food Ordering System is a console-based application designed to simplify and automate the process of ordering food from restaurants. It caters to two primary user roles: customers and restaurant owners. This system allows customers to register, log in, browse available food items, place orders, and view their order history. Restaurant owners can manage their restaurants, including adding, updating, and deleting food items, viewing orders, and managing restaurant information. The application ensures a seamless and intuitive experience for both user roles through its well-structured user interface and functionalities.

Functionalities
User Registration and Login

Registration
New Users: Users can create a new account by providing necessary details such as username, password, and role (customer or owner).
Unique Username: The system ensures that the username is unique to avoid conflicts.

Login
Returning Users: Registered users can log in by entering their username and password.
Role-Based Access: Based on the role (customer or owner), the system directs users to the appropriate functionalities.

Customer Functionalities

Browsing Food Items
View Available Food: Customers can view a list of available food items from various restaurants.
Placing Orders

Viewing Order History
Order Details: Customers can view their past orders, including order date, items, total cost, and status.

Owner Functionalities
Restaurant Management
Create Restaurant: Owners can create a new restaurant by providing details such as restaurant name, location, and cuisine type.
Update Restaurant: Owners can update restaurant details as needed.
Delete Restaurant: Owners can delete a restaurant from the system.
Food Item Management
Add Food Item: Owners can add new food items to their restaurant's menu by specifying details such as name, description, price, and availability.
Update Food Item: Owners can update existing food items to change details or adjust availability.
Delete Food Item: Owners can remove food items from their menu.

Order Management
View Orders: Owners can view orders placed by customers for their restaurant.
Update Order Status: Owners can update the status of orders (e.g., preparing, dispatched, delivered).

Demonstration Data
Pre-Populated Data: The system initializes with some demonstration data for testing and demonstration purposes. This includes sample users (both customers and owners), restaurants, and food items.

Usage Instructions

Starting the System
Run the Application: Execute the Main class to start the application.
Welcome Screen: The system displays a welcome message and presents the main menu with options to register, log in, or exit.

Customer Workflow
Register/Login: Register as a new customer or log in if you already have an account.
Browse Food Items: After logging in, browse available food items from various restaurants.

Place Order: Add desired food items to the cart, proceed to checkout, and place the order.
View Order History: Access your order history to view past orders or reorder items.

Owner Workflow
Register/Login: Register as a new owner or log in if you already have an account.
Manage Restaurant: Create a new restaurant or manage existing ones.
Manage Food Items: Add, update, or delete food items for your restaurant.
View Orders: Check orders placed by customers and update their status as needed.

Technical Overview
Singleton Pattern
UIClient: Manages the user interface and ensures only one instance of the client is created using the singleton pattern.
Controllers: Each controller (UserController, RestaurantController, FoodItemController, OrderController) follows the singleton pattern to manage respective entities and ensure consistent data handling.

Main Class
Initialization: The Main class initializes the UIClient and starts the application by calling the start method.
Main Loop: The start method in UIClient handles the main loop of the application, presenting the main menu and navigating through different functionalities based on user input.
Data Storage
In-Memory Data: For simplicity, the system uses in-memory data structures (e.g., lists) to store users, restaurants, food items, and orders.
Demo Data: The system initializes with demonstration data for easy testing and demonstration.
