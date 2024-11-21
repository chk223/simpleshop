package simpleshop.shop.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.exception.cart.CartException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Repository
public class CartMemoryRepository implements CartRepository{
    // 카트 아이템들 (유저 개개인의 카트) key:ItemId, value: CartItem(Item, quantity)
    private static final Map<UUID, Map<UUID, CartItem> > cartItemStorage = new HashMap<>();//static

    @Override
    public void addItem(User user, Item item) {
        Map<UUID, CartItem> cart = cartItemStorage.getOrDefault(user.getId(), new HashMap<>()); // 특정 유저의 장바구니 -> 없다면 만들어줌.
        CartItem cartItem = cart.getOrDefault(item.getItemId(), new CartItem(item));
        cartItem.increaseQuantity();
        cart.put(item.getItemId(), cartItem);
        cartItemStorage.put(user.getId(), cart);
    }

    @Override
    public void deleteItem(User user, Item item) {
        Map<UUID, CartItem> cart = cartItemStorage.getOrDefault(user.getId(), new HashMap<>()); // 특정 유저의 장바구니
        CartItem cartItem = cart.getOrDefault(item.getItemId(), new CartItem(item));
        if(cartItem == null) {
            log.warn("해당 아이템이 장바구니에 없습니다.");
        }
        else {
            cartItem.decreaseQuantity();
            if(cartItem.getQuantity() == 0) {
                cart.remove(item.getItemId());
            }
            cart.put(item.getItemId(), cartItem);
            cartItemStorage.put(user.getId(),cart);
        }
    }

    @Override
    public Map<UUID, CartItem> findUserCartItems(User user) {
        return cartItemStorage.get(user.getId());
    }

    @Override
    public void clearStore() {
        cartItemStorage.clear();
    }

}
