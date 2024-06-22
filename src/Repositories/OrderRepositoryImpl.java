package Repositories;
import Models.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private List<Order> orders;
    private static OrderRepositoryImpl instance;

    private OrderRepositoryImpl() {
        orders = new ArrayList<>();
    }

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }

    @Override
    public List<Order> findByCustomerId(String customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    @Override
    public List<Order> findByRestaurantId(String restaurantId) {
        List<Order> restaurantOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getRestaurantId().equals(restaurantId)) {
                restaurantOrders.add(order);
            }
        }
        return restaurantOrders;
    }

    public Order findById(String orderId) {
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public void save(Order order) {
        orders.add(order);
    }

    public void update(Order order) {
        int index = -1;
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(order.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            orders.set(index, order);
        }
    }
}
