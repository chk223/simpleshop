package simpleshop.shop.domain;

public class DiscountAmount {
    double fixedDiscountAmount;
    double percentageDiscountAmount;

    public DiscountAmount() {
        this.fixedDiscountAmount = 1000;
        this.percentageDiscountAmount = 10;
    }

    public double getFixedDiscountAmount() {
        return fixedDiscountAmount;
    }

    public double getPercentageDiscountAmount() {
        return percentageDiscountAmount;
    }
}
