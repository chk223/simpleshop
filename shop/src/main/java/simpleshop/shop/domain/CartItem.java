package simpleshop.shop.domain;

import java.util.UUID;

public class CartItem {
    private Item item;
    private Cart cart;
    private Integer quantity;

    public CartItem(Item item, Cart cart, Integer quantity) {
        this.item = item;
        this.cart = cart;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
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
