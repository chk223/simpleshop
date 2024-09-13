package simpleshop.shop.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.Item;

import java.util.*;

@Repository
public class ItemMemoryRepository implements ItemRepository{
    private static final Map<UUID, Item> itemStorage = new HashMap<>();//static
    @Override
    public Item save(Item item) {
        itemStorage.put(item.getItemId(), item);
        return item;
    }
    @Override
    public Item findById(UUID id) {
        return itemStorage.get(id);
    }
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(itemStorage.values());
    }
    @Override
    public Item update(UUID itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        return findItem;
    }
    @Override
    public void deleteById(UUID id) {
        Item targetItem = findById(id);
        itemStorage.remove(id);
    }
    @Override
    public void clearStore() {
        itemStorage.clear();
    }
}
