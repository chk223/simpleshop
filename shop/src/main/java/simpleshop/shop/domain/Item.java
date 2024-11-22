package simpleshop.shop.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;
public class Item {
    private final UUID itemId;
    @NotBlank(message = "상품 이름은 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "상품 이름은 20자 이내여야 합니다.")
    private String itemName;
    private String imgURL;
    @NumberFormat
    @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    private double price;
    @NumberFormat
    @PositiveOrZero(message = "수량은 0 이상이어야 합니다.")
    private Integer quantity;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Item(String itemName, double price, Integer quantity, String imgURL, String description) {
        this.itemId = UUID.randomUUID();
        this.itemName = itemName;
        this.imgURL = imgURL;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
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
