package simpleshop.shop.service;

import simpleshop.shop.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    //조회
    Item getItemById(UUID id);
    List<Item> getAllItems();
    //등록
    Item addItem(String itemName, Integer price, Integer quantity);
    //수정
    Item update(UUID id, Item item);
    //삭제
    void deleteById(UUID id);
}
