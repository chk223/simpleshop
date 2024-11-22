package simpleshop.shop.domain;

public class CartItem {
    private Item item;
    private Integer quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 0;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity = quantity + 1;
    }
    public void decreaseQuantity() {
        this.quantity = quantity - 1;
    }
}
