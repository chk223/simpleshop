package simpleshop.shop.repository;

import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderMemoryRepository implements OrderRepository{

    private static final Map<UUID, Order> orderStorage = new HashMap<>();
    @Override
    public Order create(User user) {
        Cart cart = user.getCart();
        Map<UUID, CartItem> items = cart.getCartItems();
        LocalDate localDate = LocalDate.now();
        double[] totalAmount = {0};
        items.forEach((id ,item) -> {
            totalAmount[0] += item.getItem().getPrice()*item.getQuantity();
        });
        Order order = new Order(user, items, localDate, totalAmount[0]);
        orderStorage.put(order.getId(),order);
        return order;
    }

    @Override
    public Order getOrderById(UUID orderId) {
        return orderStorage.get(orderId);
    }

    @Override
    public Map<UUID, Order> getOrdersByUserId(User user) {
        return user.getOrders();
    }

    @Override
    public Map<UUID, Order> getAllOrders() {
        return orderStorage;
    }

    @Override
    public void discount() {

    }

    @Override
    public void cancel(UUID orderId) {
        orderStorage.remove(orderId);
    }

    @Override
    public void statusManagement(Order order, OrderStatus status) {
        order.setStatus(status);
    }

    @Override
    public void payment() {

    }

    @Override
    public void clearStore() {
        orderStorage.clear();
    }
}
