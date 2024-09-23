package simpleshop.shop.discount;

import simpleshop.shop.domain.User;

public interface DiscountPolicy {
    double discount(User user, double price);
}
