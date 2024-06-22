package Services;
import Models.Order;
import java.util.List;

public interface OrderService {
    void placeOrder(Order order);
    List<Order> getOrdersByCustomerId(String customerId);
    List<Order> getOrdersByRestaurantId(String restaurantId);
    void updateOrderStatus(String orderId, String status);

}
