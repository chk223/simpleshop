package simpleshop.shop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.ItemForm;
import simpleshop.shop.domain.User;
import simpleshop.shop.service.ItemService;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Slf4j
@SessionAttributes("user")
public class ItemViewController {
    private final ItemService itemService;
    /**
     * 상품 조회
     */

    @GetMapping("/add-item")
    public String addItemForm(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "addItem";  // addItem.html 페이지로 이동
    }
    /**
     * 상품 등록
     */
    @PostMapping("/add-item")
    public String addItem(@ModelAttribute ItemForm itemForm) {
        Item savedItem = itemService.addItem(itemForm.getItemName(), itemForm.getPrice(), itemForm.getQuantity(), itemForm.getImgURL());
//        log.info("ItemName={}, ItemPrice={}, ItemQuantity={}, ItemImg={}", savedItem.getItemName(), savedItem.getPrice(), savedItem.getQuantity(), savedItem.getImgURL());
        return "redirect:/";
    }

    /**
     * 상품 수정
     */
    @GetMapping("/update-item/{id}")
    public String updateItemForm(@PathVariable("id") UUID itemId, Model model) {
        User user = (User) model.getAttribute("user");
        if (user != null) {
            Item item = itemService.getItemById(itemId);
            if(item != null) {
                model.addAttribute("item", item);
                return "updateItem";
            }
            else {
                return "redirect:/";
            }
        }
        else return "redirect:/";
    }
    @PostMapping("/update-item/{id}")
    public String updateItem(@PathVariable("id") UUID itemId, @ModelAttribute Item itemForm) {
        Item updatedItem = itemService.update(itemId,itemForm);
        if(updatedItem != null) {
            return "redirect:/";
        }
        else {
            return "redirect:/item/update-item" + itemId;
        }
    }

    /**상품 삭제*/
    @RequestMapping(value = "/delete-item/{id}", method = RequestMethod.POST)
    public String deleteItem(@PathVariable("id") UUID itemId) {
        log.info("itemId={}", itemId);
        itemService.deleteById(itemId);
        return "redirect:/";
    }
}
