package simpleshop.shop.service;

import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;

import java.util.Map;
import java.util.UUID;

public interface CartService {
    //유저 장바구니 조회
    Map<UUID, CartItem> getCartItems(User user);
    //장바구니에 아이템 추가
    public void addItemToCart(User user, Item item);
    //수정(삭제)
    void deleteItemFromCart(User user, Item item);
}
