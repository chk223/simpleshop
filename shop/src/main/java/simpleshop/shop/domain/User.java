package simpleshop.shop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {
    private String userId;
    private String userPassword;
    private Cart cart;

    public User(String userId, String userPassword, Cart cart) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.cart = cart;
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
