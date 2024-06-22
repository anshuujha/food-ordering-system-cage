package Repositories;
import Models.Order;
import java.util.List;
public interface OrderRepository {
    List<Order> findByCustomerId(String customerId);
    List<Order> findByRestaurantId(String restaurantId);
    Order findById(String orderId);
    void update(Order order);
}


