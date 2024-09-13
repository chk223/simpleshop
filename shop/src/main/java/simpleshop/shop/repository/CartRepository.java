package simpleshop.shop.repository;

import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;

import java.util.Map;
import java.util.UUID;

public interface CartRepository {
    public CartItem findCartItem(User user, Item item);
    public void addNewItem(User user, Item item);
    public void addItemCount(User user, CartItem cartItem);
    public void deleteItem(User user, Item item);
    public Cart findUserCart(User user);
    public Map<UUID, CartItem> findUserCartItems(User user);
    public void clearStore();
}
