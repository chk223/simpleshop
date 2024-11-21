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
import java.util.List;
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
        User userA = new User("UserA", "UserApw","userName");
        userRepository.join(userA);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        cartRepository.addItem(userA,itemA);
        cartRepository.addItem(userA,itemB);
        //when
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(userA);
        Order order = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,order.getId());
        //then
        assertThat(userA.getOrders().size()).isEqualTo(1);
        List<UUID> orders = userA.getOrders();
        assertThat(orders.get(0)).isEqualTo(order.getId());
    }

    @Test
    void orderDetail() {
        //given
        User userA = new User("UserA", "UserApw","userName");
        userRepository.join(userA);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        cartRepository.addItem(userA,itemA);
        cartRepository.addItem(userA,itemB);
        //when
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(userA);
        Order order = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,order.getId());
        Order orderDetail = orderRepository.getOrderById(order.getId());
        //then
        assertThat(orderDetail.getId()).isEqualTo(order.getId());
    }

    @Test
    void userOrders() {
        //given
        User userA = new User("UserA", "UserApw","userName");
        userRepository.join(userA);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        cartRepository.addItem(userA,itemA);
        cartRepository.addItem(userA,itemB);
        //when
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(userA);
        Order order = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,order.getId());
        Map<UUID, Order> userOrders = orderRepository.getOrdersByUserId(userA);
        //then
        assertThat(userOrders.size()).isEqualTo(1);
        assertThat(userOrders.get(order.getId())).isEqualTo(order);
    }

    @Test
    void allOrders() {
        //given
        User userA = new User("UserA", "UserApw","userNameA");
        User userB = new User("UserB", "UserBpw","userNameB");
        userRepository.join(userA);
        userRepository.join(userB);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        cartRepository.addItem(userA,itemA);
        cartRepository.addItem(userA,itemB);
        cartRepository.addItem(userB,itemA);
        cartRepository.addItem(userB,itemB);
        //when
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(userA);
        Order orderA = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,orderA.getId());
        Order orderB = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,orderB.getId());
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
        Cart cartA = new Cart(userA);
        userRepository.join(userA);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi");
        cartRepository.addItem(userA,itemA);
        cartRepository.addItem(userA,itemB);
        //when
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(userA);
        Order order = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,order.getId());
        orderRepository.cancel(order.getId());
        //then
        assertThat(orderRepository.getAllOrders().size()).isEqualTo(0);
    }

    @Test
    void manageOrderStatus() {
        //given
        Map<UUID, Order> orders = new HashMap<>();
        User userA = new User("UserA", "UserApw","userName");
        Cart cartA = new Cart(userA);
        userRepository.join(userA);
        Item itemA = new Item("ItemA", 10000, 10,null,"hi");
        Item itemB = new Item("ItemB", 20000, 20,null,"hi" );
        cartRepository.addItem(userA,itemA);
        cartRepository.addItem(userA,itemB);
        Map<UUID, CartItem> cartItems = cartRepository.findUserCartItems(userA);
        Order order = orderRepository.create(userA,cartItems);
        userRepository.addOrder(userA,order.getId());
        //when
        orderRepository.statusManagement(order.getId(),OrderStatus.PROCESSING);
        //then
        OrderStatus status = OrderStatus.PROCESSING;
        Order targetOrder = orderRepository.getOrderById(order.getId());
        assertThat(targetOrder.getStatus()).isEqualTo(status);
    }

    @Test
    void processPayment() {
    }
}