package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.CartItem;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.User;
import simpleshop.shop.repository.CartRepository;
import simpleshop.shop.service.CartService;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public Map<UUID, CartItem> getCartItems(User user) {
        return cartRepository.findUserCartItems(user);
    }

    @Override
    public void addItemToCart(User user, Item item) {
        CartItem findItem = cartRepository.findCartItem(user, item);
        if(findItem == null) {
            cartRepository.addNewItem(user, item);
        }
        else {
            cartRepository.addItemCount(user, findItem);
        }
    }

    @Override
    public void deleteItemFromCart(User user, Item item) {
        CartItem findCartItem = cartRepository.findCartItem(user, item);
        if(findCartItem != null) {
            cartRepository.deleteItem(user,item);
        }
        else {
            System.out.println("해당 상품이 장바구니에 없습니다");
        }
    }
}
