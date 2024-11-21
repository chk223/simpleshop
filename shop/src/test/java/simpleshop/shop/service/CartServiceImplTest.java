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

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

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
        User user = new User("UserA","1234","userAname");
        Cart cart = new Cart(user);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        cartRepository.addItem(user,itemA);
        cartRepository.addItem(user, itemB);
        //when
        Map<UUID, CartItem> userCartItems = cartRepository.findUserCartItems(user);
        //then
        assertThat(userCartItems.size()).isEqualTo(2);
    }

    @Test
    void addItemToCart() {
        //given
        User user = new User("UserA","1234","userAname");
        Cart cart = new Cart(user);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        Item itemC = new Item("ItemC", 30000,3,null,"hi");
        //when
        cartRepository.addItem(user,itemA);
        cartRepository.addItem(user, itemB);
        cartRepository.addItem(user,itemC);
        Map<UUID, CartItem> userCartItems = cartRepository.findUserCartItems(user);
        cartRepository.addItem(user,itemA);
        //then
        assertThat(userCartItems.get(itemA.getItemId())).isNotNull();
        assertThat(userCartItems.get(itemA.getItemId()).getQuantity()).isEqualTo(2);
        assertThat(userCartItems.get(itemB.getItemId()).getQuantity()).isEqualTo(1);
    }

    @Test
    void deleteItemFromCart() {
        //given
        User user = new User("UserA","1234","userAname");
        Cart cart = new Cart(user);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        Item itemC = new Item("ItemC", 30000,3,null,"hi");
        //when
        cartRepository.addItem(user,itemA);
        cartRepository.addItem(user,itemA);
        cartRepository.addItem(user, itemB);
        cartRepository.addItem(user,itemC);
        Map<UUID, CartItem> userCartItems = cartRepository.findUserCartItems(user);
        cartRepository.deleteItem(user,itemA);
        //then
        assertThat(userCartItems.get(itemA.getItemId())).isNotNull();
        assertThat(userCartItems.get(itemA.getItemId()).getQuantity()).isEqualTo(1);
        assertThat(userCartItems.get(itemB.getItemId()).getQuantity()).isEqualTo(1);
    }
}