package simpleshop.shop.domain;

import lombok.Data;

import java.util.UUID;
public class Item {
    private UUID itemId;
    private String itemName;
    private double price;
    private Integer quantity;

    public Item(String itemName, double price, Integer quantity) {
        this.itemId = UUID.randomUUID();
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
