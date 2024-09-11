package simpleshop.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import simpleshop.shop.domain.Item;
import simpleshop.shop.repository.ItemRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImpl {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    Item getItemById(UUID id){
        return itemRepository.findById(id);
    }
    List<Item> getAllItems(){
        return itemRepository.findAll();
    }
    void addItem(Item item){
        itemRepository.save(item);
    }
    void update(UUID targetId, Item item){
        itemRepository.update(targetId, item);
    }
    void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }
}
