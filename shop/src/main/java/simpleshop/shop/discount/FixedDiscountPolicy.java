package simpleshop.shop.discount;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import simpleshop.shop.domain.DiscountAmount;
import simpleshop.shop.domain.Grade;
import simpleshop.shop.domain.User;

@Primary
@Component
public class FixedDiscountPolicy implements DiscountPolicy{
    @Override
    public double discount(User user, double price) {
        DiscountAmount discountAmount = new DiscountAmount();
        if(user.getGrade() == Grade.VIP) {
            return discountAmount.getFixedDiscountAmount();
        }
        return 0;
    }
}
