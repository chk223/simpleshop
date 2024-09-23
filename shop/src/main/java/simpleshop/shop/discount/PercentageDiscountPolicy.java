package simpleshop.shop.discount;

import org.springframework.stereotype.Component;
import simpleshop.shop.domain.DiscountAmount;
import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;

@Component
public class PercentageDiscountPolicy implements DiscountPolicy{
    @Override
    public double discount(User user, double price) {
        DiscountAmount discountAmount = new DiscountAmount();
        if(user.getGrade() == Grade.VIP) {
            double discount = discountAmount.getPercentageDiscountAmount();
            return price*discount/100;
        }
        return 0;
    }
}
