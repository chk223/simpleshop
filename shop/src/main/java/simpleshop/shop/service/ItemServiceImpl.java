package simpleshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.Item;
import simpleshop.shop.repository.ItemRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public Item getItemById(UUID id) {
        return itemRepository.findById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item addItem(String itemName, Integer price, Integer quantity) {
        Item item = new Item(itemName, price, quantity);
        return itemRepository.save(item);
    }

    @Override
    public Item update(UUID id, Item item) {
        Item originalItem = getItemById(id);
        if(item.getItemName() == null) item.setItemName(originalItem.getItemName());
        if(item.getPrice() == null) item.setPrice(originalItem.getPrice());
        if(item.getQuantity() == null) item.setQuantity(originalItem.getQuantity());
        return itemRepository.update(id, item);
    }

    @Override
    public void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }
}
