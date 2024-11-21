package simpleshop.shop.service;

import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.ItemForm;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    /**상품 아이디로 상품 조회*/
    Item getItemById(UUID id);
    /**모든 상품 조회*/
    List<Item> getAllItems();
    /**상품 등록*/
    Item addItem(String itemName, double price, Integer quantity, String imgURL, String description);
    /**상품 수정*/
    Item update(UUID id, Item item);
    /**상품 삭제*/
    void deleteById(UUID id);
}
