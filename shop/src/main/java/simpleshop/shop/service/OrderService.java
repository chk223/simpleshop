package simpleshop.shop.service;

import simpleshop.shop.domain.*;

import java.util.Map;
import java.util.UUID;

public interface OrderService {
    //주문 생성
    public void createOrder(User user);
    //주문 조회
    public OrderItem orderDetail(UUID orderId);
    public Map<UUID, Order> userOrders(User user);
    public Map<UUID, Order> allOrders();
    //할인 적용
    public double applyDiscount(User user, Order order);
    //주문 취소
    public void cancelOrder(UUID orderId);
    //주문 상태 변경(접수/배송준비/배송중/배송완료)
    public void manageOrderStatus(UUID orderId, OrderStatus status);
    //결제
    public void processPayment(UUID orderId);
}
