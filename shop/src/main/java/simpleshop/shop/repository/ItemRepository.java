package simpleshop.shop.repository;

import simpleshop.shop.domain.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository {
    public Item save(Item item);
    public Item findById(UUID id);
    public List<Item> findAll();
    public void update(UUID itemId, Item updateParam);
    public void deleteById(UUID id);
    public void clearStore();
}
