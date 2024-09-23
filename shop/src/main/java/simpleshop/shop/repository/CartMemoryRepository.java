package simpleshop.shop.repository;

import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Repository
public class CartMemoryRepository implements CartRepository{

    private static final Map<UUID, CartItem> cartItemStorage = new HashMap<>();//static
    private static final Map<UUID,Cart> cartStorage = new HashMap<>();


    @Override
    public CartItem findCartItem(User user, Item item) {
        Cart userCart = user.getCart();
        Map<UUID, CartItem> cartItems = userCart.getCartItems();
        return cartItems.get(item.getItemId());
    }

    @Override
    public void addNewItem(User user, Item item) {
        CartItem cartItem = new CartItem(item, user.getCart(), item.getQuantity()!=null? item.getQuantity() : 1);
        cartItemStorage.put(cartItem.getItem().getItemId(),cartItem);
        Cart userCart = user.getCart();
        userCart.setCartItems(cartItemStorage);
        user.setCart(userCart);
    }

    @Override
    public void addItemCount(User user, CartItem cartItem) {
        cartItem.increaseQuantity(cartItem.getQuantity());
    }

    @Override
    public void deleteItem(User user, Item item) {
        cartItemStorage.remove(item.getItemId());
        Cart userCart = user.getCart();
        userCart.setCartItems(cartItemStorage);
        user.setCart(userCart);
    }

    @Override
    public Cart findUserCart(User user) {
        return user.getCart();
    }

    @Override
    public Map<UUID, CartItem> findUserCartItems(User user) {
        Cart userCart = user.getCart();
        return userCart.getCartItems();
    }

    @Override
    public void clearStore() {
        cartItemStorage.clear();
        cartStorage.clear();
    }

}
