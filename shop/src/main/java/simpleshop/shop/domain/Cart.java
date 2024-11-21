package simpleshop.shop.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cart {
    private UUID id;
    private User user;
    private Map<UUID,CartItem> cartItems;

    public Cart(User user) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.cartItems = new HashMap<>();
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

    public Map<UUID, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<UUID, CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
