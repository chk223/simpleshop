package simpleshop.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simpleshop.shop.domain.*;
import simpleshop.shop.repository.CartRepository;
import simpleshop.shop.repository.OrderRepository;
import simpleshop.shop.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void AfterEach() {
        orderRepository.clearStore();
    }

    @Test
    void createOrder() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userName");
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cartA = new Cart(userA,cartItem);
        userRepository.join(userA);
        userA.setCart(cartA);
        Item itemA = new Item("ItemA", 10000, 10,null);
        Item itemB = new Item("ItemB", 20000, 20,null);
        cartRepository.addNewItem(userA,itemA);
        cartRepository.addNewItem(userA,itemB);
        //when
        Order order = orderRepository.create(userA);
        orders.put(order.getId(), order);
        userRepository.addOrder(userA,orders);
        //then
        assertThat(userA.getOrders().size()).isEqualTo(1);
        Order userOrder = userA.getOrders().get(order.getId());
        assertThat(order.getId()).isEqualTo(userOrder.getId());
    }

    @Test
    void orderDetail() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userName");
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cartA = new Cart(userA,cartItem);
        userRepository.join(userA);
        userA.setCart(cartA);
        Item itemA = new Item("ItemA", 10000, 10,null);
        Item itemB = new Item("ItemB", 20000, 20,null);
        cartRepository.addNewItem(userA,itemA);
        cartRepository.addNewItem(userA,itemB);
        //when
        Order order = orderRepository.create(userA);
        orders.put(order.getId(), order);
        userRepository.addOrder(userA,orders);
        Order orderDetail = orderRepository.getOrderById(order.getId());
        //then
        assertThat(orderDetail.getId()).isEqualTo(order.getId());
    }

    @Test
    void userOrders() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userName");
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cartA = new Cart(userA,cartItem);
        userRepository.join(userA);
        userA.setCart(cartA);
        Item itemA = new Item("ItemA", 10000, 10,null);
        Item itemB = new Item("ItemB", 20000, 20,null);
        cartRepository.addNewItem(userA,itemA);
        cartRepository.addNewItem(userA,itemB);
        //when
        Order order = orderRepository.create(userA);
        orders.put(order.getId(), order);
        userRepository.addOrder(userA,orders);
        Map<UUID, Order> userOrders = orderRepository.getOrdersByUserId(userA);
        //then
        assertThat(userOrders.size()).isEqualTo(1);
        assertThat(userOrders.get(order.getId())).isEqualTo(order);
    }

    @Test
    void allOrders() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userNameA");
        User userB = new User("UserB", "UserBpw","userNameB");
        Map<UUID, CartItem> cartItemA = new HashMap<>();
        Map<UUID, CartItem> cartItemB = new HashMap<>();
        Cart cartA = new Cart(userA,cartItemA);
        Cart cartB = new Cart(userB,cartItemB);
        userRepository.join(userA);
        userRepository.join(userB);
        userA.setCart(cartA);
        userB.setCart(cartB);
        Item itemA = new Item("ItemA", 10000, 10,null);
        Item itemB = new Item("ItemB", 20000, 20,null);
        cartRepository.addNewItem(userA,itemA);
        cartRepository.addNewItem(userA,itemB);
        cartRepository.addNewItem(userB,itemA);
        cartRepository.addNewItem(userB,itemB);
        //when
        Order orderA = orderRepository.create(userA);
        orders.put(orderA.getId(), orderA);
        userRepository.addOrder(userA,orders);
        Order orderB = orderRepository.create(userA);
        orders.put(orderB.getId(), orderB);
        userRepository.addOrder(userB,orders);
        Map<UUID, Order> allOrders = orderRepository.getAllOrders();
        //then
        assertThat(allOrders.size()).isEqualTo(2);
    }

    @Test
    void applyDiscount() {
        //given
        //when
        //then
    }

    @Test
    void cancelOrder() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userName");
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cartA = new Cart(userA,cartItem);
        userRepository.join(userA);
        userA.setCart(cartA);
        Item itemA = new Item("ItemA", 10000, 10,null);
        Item itemB = new Item("ItemB", 20000, 20,null);
        cartRepository.addNewItem(userA,itemA);
        cartRepository.addNewItem(userA,itemB);
        //when
        Order order = orderRepository.create(userA);
        orders.put(order.getId(), order);
        userRepository.addOrder(userA,orders);
        orderRepository.cancel(order.getId());
        orders.remove(order.getId());
        //then

        assertThat(orderRepository.getAllOrders().size()).isEqualTo(0);
    }

    @Test
    void manageOrderStatus() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userName");
        Map<UUID, CartItem> cartItem = new HashMap<>();
        Cart cartA = new Cart(userA,cartItem);
        userRepository.join(userA);
        userA.setCart(cartA);
        Item itemA = new Item("ItemA", 10000, 10,null);
        Item itemB = new Item("ItemB", 20000, 20,null);
        cartRepository.addNewItem(userA,itemA);
        cartRepository.addNewItem(userA,itemB);
        Order order = orderRepository.create(userA);
        orders.put(order.getId(), order);
        userRepository.addOrder(userA,orders);
        //when
        orderRepository.statusManagement(order,OrderStatus.PROCESSING);
        //then
        OrderStatus status = OrderStatus.PROCESSING;
        Order targetOrder = orderRepository.getOrderById(order.getId());
        assertThat(targetOrder.getStatus()).isEqualTo(status);
    }

    @Test
    void processPayment() {
    }
}