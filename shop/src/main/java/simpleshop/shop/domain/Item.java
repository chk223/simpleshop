package simpleshop.shop.domain;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;
public class Item {
    private final UUID itemId;
    private String itemName;
    private String imgURL;
    private double price;
    private Integer quantity;

    public Item(String itemName, double price, Integer quantity, String imgURL) {
        this.itemId = UUID.randomUUID();
        this.itemName = itemName;
        this.imgURL = imgURL;
        this.price = price;
        this.quantity = quantity;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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
