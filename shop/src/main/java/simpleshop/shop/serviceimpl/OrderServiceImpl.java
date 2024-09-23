package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simpleshop.shop.discount.DiscountPolicy;
import simpleshop.shop.domain.*;
import simpleshop.shop.repository.OrderRepository;
import simpleshop.shop.repository.UserRepository;
import simpleshop.shop.service.OrderService;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DiscountPolicy discountPolicy;

    @Override
    public void createOrder(User user) {
        Map<UUID, Order> orders = user.getOrders();
        Order order = orderRepository.create(user);
        orders.put(order.getId(),order);
        userRepository.addOrder(user,orders);
    }

    @Override
    public OrderItem orderDetail(UUID orderId) {
        Order order = orderRepository.getOrderById(orderId);
        return new OrderItem(order.getId(), order.getOrderItems(), order.getStatus(),order.getOrderDate(),order.getTotalAmount());
    }

    @Override
    public Map<UUID, Order> userOrders(User user) {
        return orderRepository.getOrdersByUserId(user);
    }

    @Override
    public Map<UUID, Order> allOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public double applyDiscount(User user, Order order) {
        return discountPolicy.discount(user, order.getTotalAmount());
    }

    @Override
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.getOrderById(orderId);
        User user = order.getUser();
        Map<UUID, Order> orders = user.getOrders();
        orders.remove(orderId);
        userRepository.addOrder(user, orders);
        orderRepository.cancel(orderId);
    }

    @Override
    public void manageOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.getOrderById(orderId);
        orderRepository.statusManagement(order, status);
    }

    @Override
    public void processPayment(UUID orderId) {

    }
}
