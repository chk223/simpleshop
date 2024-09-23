package simpleshop.shop.repository;

import simpleshop.shop.domain.Order;
import simpleshop.shop.domain.OrderItem;
import simpleshop.shop.domain.OrderStatus;
import simpleshop.shop.domain.User;

import java.util.Map;
import java.util.UUID;

public interface OrderRepository {
    //주문 생성
    public Order create(User user);
    //주문 조회
    public Order getOrderById(UUID orderId);
    public Map<UUID, Order> getOrdersByUserId(User user);
    public Map<UUID, Order> getAllOrders();
    //할인 적용
    public void discount();
    //주문 취소
    public void cancel(UUID orderId);
    //주문 상태 변경(접수/배송준비/배송중/배송완료)
    public void statusManagement(Order order, OrderStatus status);
    //결제
    public void payment();
    public void clearStore();
}
