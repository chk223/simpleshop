package simpleshop.shop.domain;

import lombok.Data;

@Data
public class ItemForm {
    private String itemName;
    private double price;
    private Integer quantity;

    public ItemForm(String itemName, double price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
