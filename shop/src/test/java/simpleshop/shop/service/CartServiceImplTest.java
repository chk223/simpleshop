package simpleshop.shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.repository.CartRepository;
import simpleshop.shop.repository.ItemRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartServiceImplTest {
    @Autowired
    CartRepository cartRepository;

    @AfterEach
    void afterEach() {
        cartRepository.clearStore();
    }

    @Test
    void getCartItems() {
        //given
        User user = new User("UserA","1234",null);
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cart = new Cart(user, cartItem);
        user.setCart(cart);
        Item itemA = new Item("ItemA", 10000, 10);
        Item itemB = new Item("ItemB", 20000, 20);
        cartRepository.addNewItem(user,itemA);
        cartRepository.addNewItem(user, itemB);
        //when
        Map<UUID, CartItem> userCartItems = cartRepository.findUserCartItems(user);
        //then
        assertThat(userCartItems.size()).isEqualTo(2);
    }

    @Test
    void addItemToCart() {
        //given
        User user = new User("UserA","1234",null);
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cart = new Cart(user, cartItem);
        user.setCart(cart);
        Item itemA = new Item("ItemA", 10000, 10);
        Item itemB = new Item("ItemB", 20000, 20);
        Item itemC = new Item("ItemC", 30000,3);
        //when
        cartRepository.addNewItem(user,itemA);
        cartRepository.addNewItem(user, itemB);
        cartRepository.addNewItem(user,itemC);
        Cart userCart = cartRepository.findUserCart(user);
        CartItem cartItemA = cartRepository.findCartItem(user, itemA);
        CartItem cartItemB = cartRepository.findCartItem(user, itemB);
        CartItem cartItemC = cartRepository.findCartItem(user, itemC);
        cartRepository.addItemCount(user,cartItemC);
        //then
        assertThat(userCart.getUser()).isSameAs(user);
        assertThat(cartItemA.getId()).isEqualTo(itemA.getItemId());
        assertThat(cartItemA.getQuantity()).isEqualTo(10);
        assertThat(cartItemB.getId()).isEqualTo(itemB.getItemId());
        assertThat(cartItemC.getQuantity()).isEqualTo(4);
    }

    @Test
    void deleteItemFromCart() {
        //given
        User user = new User("UserA","1234",null);
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cart = new Cart(user, cartItem);
        user.setCart(cart);
        Item itemA = new Item("ItemA", 10000, 10);
        Item itemB = new Item("ItemB", 20000, 20);
        Item itemC = new Item("ItemC", 30000,3);
        cartRepository.addNewItem(user,itemA);
        cartRepository.addNewItem(user, itemB);
        cartRepository.addNewItem(user,itemC);
        //when
        cartRepository.deleteItem(user,itemA);
        //then
        assertThat(cartRepository.findUserCartItems(user).size()).isEqualTo(2);
        assertThat(cartRepository.findCartItem(user,itemA)).isNull();
        assertThat(cartRepository.findCartItem(user,itemB).getId()).isSameAs(itemB.getItemId());
        assertThat(cartRepository.findCartItem(user,itemC).getId()).isSameAs(itemC.getItemId());
    }
}