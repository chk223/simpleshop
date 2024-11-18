package simpleshop.shop.domain;

import lombok.Data;

@Data
public class ItemForm {
    private String itemName;
    private double price;
    private Integer quantity;
    private String imgURL;

    public ItemForm() {
    }

    public ItemForm(String itemName, double price, Integer quantity, String imgURL) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgURL=imgURL;
    }
}
