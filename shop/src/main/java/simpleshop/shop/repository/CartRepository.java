package simpleshop.shop.repository;

import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;

import java.util.Map;
import java.util.UUID;

public interface CartRepository {
    public void addItem(User user, Item item);
    public void deleteItem(User user, Item item);
    public Map<UUID, CartItem> findUserCartItems(User user);
    public void clearStore();
}
