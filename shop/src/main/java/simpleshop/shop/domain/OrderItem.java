package simpleshop.shop.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

public class OrderItem {
    private UUID id;
    private Map<UUID, CartItem> orderItems;
    private OrderStatus status;
    private LocalDate orderDate;
    private double totalAmount;

    public OrderItem(UUID id, Map<UUID, CartItem> orderItems, OrderStatus status, LocalDate orderDate, double totalAmount) {
        this.id = id;
        this.orderItems = orderItems;
        this.status = status;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public UUID getId() {
        return id;
    }

    public Map<UUID, CartItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
