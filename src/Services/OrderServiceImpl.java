package Services;
import Models.Order;
import Repositories.OrderRepository;
import Repositories.OrderRepositoryImpl;
import java.util.List;
import java.util.ArrayList;
public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;
    private List<Order> orders = new ArrayList<>();
    private OrderRepository orderRepository;
    private OrderServiceImpl() {}


    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void placeOrder(Order order) {
        orders.add(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(String customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    @Override
    public List<Order> getOrdersByRestaurantId(String restaurantId) {
        List<Order> restaurantOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getRestaurantId().equals(restaurantId)) {
                restaurantOrders.add(order);
            }
        }
        return restaurantOrders;
    }
    @Override
    public void updateOrderStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderRepository.update(order);
        } else {
            throw new IllegalArgumentException("Order with id " + orderId + " not found.");
        }
    }
}
