package simpleshop.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.ItemForm;
import simpleshop.shop.service.ItemService;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/item_api")
@RequiredArgsConstructor
@Slf4j
public class ItemController {
    private final ItemService itemService;


    /**
     * 상품 조회
     */
    @GetMapping("/all-items")
    public List<Item> allItems() {
        System.out.println(itemService.getAllItems());
        return itemService.getAllItems();
    }

    /**
     * 상품 등록
     */
    @PostMapping("/add-item")
    public void addItem(@RequestBody ItemForm itemForm) {
        Item savedItem = itemService.addItem(itemForm.getItemName(), itemForm.getPrice(), itemForm.getQuantity(), itemForm.getImgURL());
        log.info("ItemName={}, ItemPrice={}, ItemQuantity={}", savedItem.getItemName(), savedItem.getPrice(), savedItem.getQuantity());
    }

    /**
     * 상품 수정
     */
    @PostMapping("/update-item")
    public void updateItem(@RequestBody Item item) {
        Item updateItem = itemService.update(item.getItemId(), item);
        log.info("updateItem={}", updateItem.getItemName());
    }

    /**상품 삭제*/
    @PostMapping("/delete-item")
    public void deleteItem(@RequestBody UUID id) {
        log.info("itemId={}", id);
        itemService.deleteById(id);
    }
}