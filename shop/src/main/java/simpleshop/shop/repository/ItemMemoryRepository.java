package simpleshop.shop.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.Item;

import java.util.*;

@Repository
public class ItemMemoryRepository implements ItemRepository{
    private static final Map<UUID, Item> storage = new HashMap<>();//static
    private static long sequence = 0L;// static

    public Item save(Item item) {
        storage.put(item.getItemId(), item);
        return item;
    }

    public Item findById(UUID id) {
        return storage.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void update(UUID itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }
    public void deleteById(UUID id) {
        Item targetItem = findById(id);
        storage.remove(id);
    }

    public void clearStore() {
        storage.clear();
    }
}
