package simpleshop.shop.controller.view;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simpleshop.shop.domain.Cart;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.service.CartService;
import simpleshop.shop.service.ItemService;
import simpleshop.shop.service.UserService;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/cart")
public class CartViewController {
    private final CartService cartService;

    //장바구니 아이템 출력
    @GetMapping("/cart-items")
    public String addCartForm(Model model) {
        User user = (User)model.getAttribute("user");
//        log.info("user={}",user);
        if(user == null) {
            throw new IllegalStateException("로그인 해주세요.");
        }
        Map<UUID, CartItem> cartItems = cartService.getCartItems(user);
        if (cartItems == null || cartItems.isEmpty()) {
            model.addAttribute("cart", Collections.emptyList());
        } else {
            List<CartItem> userCart = new ArrayList<>(cartItems.values());
            for(CartItem item : userCart){
                log.info("장바구니에 있는 cartItem={}", item.getItem().getItemName());
            }
            model.addAttribute("cart", userCart);
        }
        return "myCart";
    }

    //장바구니에 아이템 추가
    @PostMapping("/add-cart/{id}")
    public String addCart(@PathVariable("id") UUID itemId, Model model) {
        User user = (User)model.getAttribute("user");
//        log.info("user={}, item={}", user.getUserName(), itemId);
        if(user==null) {
            throw new IllegalStateException("로그인 필요");
        }
        cartService.addItemToCart(user,itemId);
        return "redirect:/cart/cart-items";
    }


    //장바구니에서 아이템 삭제
    @RequestMapping(value = "/delete-cart/{id}", method = RequestMethod.POST)
    public String deleteCartItem(@PathVariable("id") UUID itemId, Model model) {
        User user = (User)model.getAttribute("user");
        if(user==null) {
            throw new IllegalStateException("로그인 필요");
        }
        else {
            cartService.deleteItemFromCart(user,itemId);
            return "redirect:/cart/cart-items";
        }
    }
}
