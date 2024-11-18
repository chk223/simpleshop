package simpleshop.shop.repository;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import simpleshop.shop.domain.DefaultImage;
import simpleshop.shop.domain.Item;
import simpleshop.shop.domain.ItemForm;

import java.util.*;

@Repository
public class ItemMemoryRepository implements ItemRepository{
    private static final Map<UUID, Item> itemStorage = new HashMap<>();//static
    @Override
    public Item save(Item item) {
        if(item.getImgURL() == null || item.getImgURL().isEmpty()) {
            item.setImgURL(DefaultImage.ITEM.getUrl());
        }
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
        findItem.setImgURL(updateParam.getImgURL());
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
