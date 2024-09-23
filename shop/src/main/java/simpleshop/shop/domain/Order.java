package simpleshop.shop.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class Order {
    private UUID id;
    private User user;
    private Map<UUID, CartItem> orderItems;
    private OrderStatus status;
    private LocalDate orderDate;
    private double totalAmount;
    private double discountPrice;

    public Order(User user, Map<UUID, CartItem> orderItems, LocalDate orderDate, double totalAmount) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.orderItems = orderItems;
        this.status = OrderStatus.PROCESSING;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.discountPrice = 0;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<UUID, CartItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Map<UUID, CartItem> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
