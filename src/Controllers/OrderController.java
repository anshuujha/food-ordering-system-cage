package Controllers;
import Models.Order;
import Services.OrderServiceImpl;

import java.util.List;
public class OrderController {
    private final OrderServiceImpl orderServiceImpl;
    private static OrderController instance;


    public static OrderController getInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
    public OrderController() {
        this.orderServiceImpl = OrderServiceImpl.getInstance();
    }

    public void placeOrder(Order order) {
        orderServiceImpl.placeOrder(order);
    }

    public List<Order> getOrdersByCustomerId(String customerId) {
        return orderServiceImpl.getOrdersByCustomerId(customerId);
    }

    public List<Order> getOrdersByRestaurantId(String restaurantId) {
        return orderServiceImpl.getOrdersByRestaurantId(restaurantId);
    }
    public void updateOrderStatus(String orderId, String status) {
        orderServiceImpl.updateOrderStatus(orderId, status);
    }
}
