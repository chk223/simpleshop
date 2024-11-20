package simpleshop.shop.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.service.CartService;
import simpleshop.shop.service.ItemService;
import simpleshop.shop.service.UserService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final ItemService itemService;
    //유저 장바구니 조회
    @GetMapping("/user-cart")
    public Map<UUID, CartItem> showUserCart(String userId) {
        User user = userService.findUser(userId);
        return cartService.getCartItems(user);
    }
    //장바구니에 아이템 추가
    @PostMapping("/add-cart")
    public void addCart(String userId, UUID itemId) {
        User user = userService.findUser(userId);
        Item item = itemService.getItemById(itemId);
        cartService.addItemToCart(user, item);
    }
    //수정(삭제)
    @PostMapping("/delete-cart-item")
    public void deleteCartItem(String userId, UUID itemId) {
        User user = userService.findUser(userId);
        Item item = itemService.getItemById(itemId);
        cartService.deleteItemFromCart(user, item);
    }
}
