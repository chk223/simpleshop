package simpleshop.shop.repository;

import simpleshop.shop.domain.*;

import java.util.Map;
import java.util.UUID;

public interface OrderRepository {
    //주문 생성
    public Order create(User user, Map<UUID, CartItem> cartItems);
    //주문 조회
    /**주문번호를 통해 주문 조회*/
    public Order getOrderById(UUID orderId);
    /**해당 유저의 주문 목록 반환*/
    public Map<UUID, Order> getOrdersByUserId(User user);
    /**모든 유저의 주문 반환*/
    public Map<UUID, Order> getAllOrders();
    //할인 적용
    public void discount();
    /**주문 취소*/
    public void cancel(UUID orderId);
    //주문 상태 변경(접수/배송준비/배송중/배송완료)
    public void statusManagement(UUID orderId, OrderStatus status);
    //결제
    public void payment();
    public void clearStore();
}
