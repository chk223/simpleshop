package simpleshop.shop.domain;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ItemForm {
    @NotBlank(message = "상품 이름은 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "상품 이름은 20자 이내여야 합니다.")
    private String itemName;
    private String imgURL;
    @Max(1000000)
    @Min(1000)
    @PositiveOrZero(message = "가격은 0 이상이어야 합니다.")
    private double price;
    @Min(0)
    @Max(10000)
    @PositiveOrZero(message = "수량은 0 이상이어야 합니다.")
    private Integer quantity;
    private String description;

    public ItemForm() {
    }

    public ItemForm(String itemName, double price, Integer quantity, String imgURL, String description) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
        this.imgURL=imgURL;
        this.description=description;
    }
}
