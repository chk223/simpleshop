package simpleshop.shop.serviceimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.ItemForm;
import simpleshop.shop.repository.ItemRepository;
import simpleshop.shop.service.ItemService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item getItemById(UUID id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item addItem(String itemName, double price, Integer quantity, String imgURL, String description) {
        Item item = new Item(itemName, price, quantity,imgURL, description);
        return itemRepository.save(item);
    }

    @Override
    public Item update(UUID id, Item item) {
        Item originalItem = getItemById(id);
        if(item.getItemName() == null) item.setItemName(originalItem.getItemName());
        if(item.getPrice() == 0) item.setPrice(originalItem.getPrice());
        if(item.getQuantity() == null) item.setQuantity(originalItem.getQuantity());
        if(item.getDescription() == null) item.setDescription(originalItem.getDescription());
        return itemRepository.update(id, item);
    }

    @Override
    public void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }
}
