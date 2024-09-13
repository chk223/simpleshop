package simpleshop.shop.domain;

import java.util.UUID;

public class CartItem {
    private UUID id;
    private Cart cart;
    private Integer quantity;

    public CartItem(UUID id, Cart cart, Integer quantity) {
        this.id = id;
        this.cart = cart;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(Integer quantity) {
        this.quantity += 1;
    }

}
