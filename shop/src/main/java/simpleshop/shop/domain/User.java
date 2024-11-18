package simpleshop.shop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private UUID id;
    private String userId;
    private String userPassword;
    private String userName;
    private Cart cart;
    private Grade grade;
    //orderId, order
    private Map<UUID, Order> orders;

    public User(String userId, String userPassword, String userName) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.cart = null;
        this.grade = Grade.BASIC;
        this.orders = null;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<UUID, Order> getOrders() {
        return orders;
    }

    public void setOrders(Map<UUID, Order> orders) {
        this.orders = orders;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
