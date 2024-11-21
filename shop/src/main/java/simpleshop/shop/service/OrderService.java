package simpleshop.shop.service;

import simpleshop.shop.domain.*;

import java.util.Map;
import java.util.UUID;

public interface OrderService {
    /**주문 생성*/
    public void createOrder(User user);
    /**주문 조회*/
    public Order orderDetail(UUID orderId);
    /**해당 유저의 주문 리스트(orderId, Order)*/
    public Map<UUID, Order> userOrders(User user);
    /**모든 유저의 주문 리스트(orderId, Order)*/
    public Map<UUID, Order> allOrders();
    /**할인 적용*/
    public double applyDiscount(User user, Order order);
    /**주문 취소*/
    public void cancelOrder(User user, UUID orderId);
    /**주문 상태 변경(접수/배송준비/배송중/배송완료)*/
    public void changeOrderStatus(UUID orderId, OrderStatus status);
    /**결제*/
    public void processPayment(UUID orderId);
}
