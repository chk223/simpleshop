package simpleshop.shop.repository;

import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderMemoryRepository implements OrderRepository{

    private static final Map<UUID, Order> orderStorage = new HashMap<>();
    @Override
    public Order create(User user, Map<UUID, CartItem> cartItems) {
        LocalDate localDate = LocalDate.now();
        double[] totalAmount = {0};//람다에서 사용하려면 배열을 사용해야 하기 때문에 배열로 총합 값 저장
        cartItems.forEach((id ,item) -> {
            totalAmount[0] += item.getItem().getPrice()*item.getQuantity();
        });
        Order order = new Order(user, cartItems, localDate, totalAmount[0]);
        orderStorage.put(order.getId(),order);
        return order;
    }
    /**주문번호를 통해 주문 조회*/
    @Override
    public Order getOrderById(UUID orderId) {
        return orderStorage.get(orderId);
    }

    /**해당 유저의 주문 목록 반환*/
    @Override
    public Map<UUID, Order> getOrdersByUserId(User user) {
        List<UUID> orderList = user.getOrders();
        Map<UUID, Order> orders = new HashMap<>();
        for(UUID id: orderList) {
            orders.put(id,orderStorage.get(id));
        }
        return orders;
    }
    /**모든 유저의 주문 반환*/
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
    public void statusManagement(UUID orderId, OrderStatus status) {
        Order order = orderStorage.get(orderId);
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
