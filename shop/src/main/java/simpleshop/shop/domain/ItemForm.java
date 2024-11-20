package simpleshop.shop.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ItemForm {
    @NotBlank(message = "상품 이름은 공백이 포함될 수 없습니다.")
    @Size(max=20,message = "상품 이름은 20자 이내여야 합니다.")
    private String itemName;
    private String imgURL;
    @NumberFormat
    @Max(1000000)
    @Min(1000)
    @NotBlank(message = "가격은 공백이 포함될 수 없습니다.")
    private double price;
    @NumberFormat
    @Min(0)
    @Max(10000)
    @NotBlank(message = "수량은 공백이 포함될 수 없습니다.")
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
