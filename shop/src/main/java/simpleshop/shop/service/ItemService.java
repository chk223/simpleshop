package simpleshop.shop.service;

import simpleshop.shop.domain.Item;

import java.util.List;

public interface ItemService {
    //조회
    Item getItemById(Long id);
    List<Item> getAllItems();
    //등록
    void addItem(Item item);
    //수정
    void update(Long id,Item item);
    //삭제
    void deleteById(Long id);
}
