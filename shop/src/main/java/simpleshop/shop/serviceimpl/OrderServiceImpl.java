package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simpleshop.shop.discount.DiscountPolicy;
import simpleshop.shop.domain.*;
import simpleshop.shop.repository.CartRepository;
import simpleshop.shop.repository.OrderRepository;
import simpleshop.shop.repository.UserRepository;
import simpleshop.shop.service.OrderService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DiscountPolicy discountPolicy;
    private final CartRepository cartRepository;

    @Override
    public void createOrder(User user) {
        List<UUID> orders = user.getOrders();
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(user);
        Order order = orderRepository.create(user,cartItems);
        userRepository.addOrder(user,order.getId());
    }

    @Override
    public Order orderDetail(UUID orderId) {
        return orderRepository.getOrderById(orderId);
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
    public void cancelOrder(User user, UUID orderId) {
        userRepository.removeOrder(user, orderId);
        orderRepository.cancel(orderId);
    }

    @Override
    public void changeOrderStatus(UUID orderId, OrderStatus status) {
        orderRepository.statusManagement(orderId, status);
    }

    @Override
    public void processPayment(UUID orderId) {

    }
}
