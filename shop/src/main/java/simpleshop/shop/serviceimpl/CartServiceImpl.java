package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
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
        cartRepository.addItem(user,item);
    }

    @Override
    public void deleteItemFromCart(User user, Item item) {
        cartRepository.deleteItem(user,item);
    }
}
