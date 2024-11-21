package simpleshop.shop.service;

import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;

import java.util.Map;
import java.util.UUID;

public interface CartService {
    /**유저의 장바구니 조회*/
    Map<UUID, CartItem> getCartItems(User user);
    /**해당 유저의 장바구니에 상품 추가(이미 있다면 개수 증가)*/
    public void addItemToCart(User user, Item item);
    /**해당 유저의 장바구니에서 상품 삭제(이미 있다면 개수 차감)*/
    void deleteItemFromCart(User user, Item item);
}
