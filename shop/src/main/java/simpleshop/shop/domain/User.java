package simpleshop.shop.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {
    private Long userId;
    private Cart cart;

    public User(Long userId, Cart cart) {
        this.userId = userId;
        this.cart = cart;
    }

    public Long getUserId() {
        return userId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
