package simpleshop.shop.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import simpleshop.shop.domain.Item;
import simpleshop.shop.repository.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }


    @Test
    void getItemById() {
        //given
        Item item = new Item("ItemA", 100, 10,null);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(savedItem.getItemId());
        assertThat(item.getItemName()).isEqualTo(findItem.getItemName());
        assertThat(item.getPrice()).isEqualTo(findItem.getPrice());
        assertThat(item.getQuantity()).isEqualTo(findItem.getQuantity());
    }

    @Test
    void getAllItems() {
        //given
        Item itemA = new Item("itemA", 100, 1,null);
        Item itemB = new Item("itemB", 200, 2,null);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        List<Item> result = itemRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA, itemB);
    }

    @Test
    void addItem() {
        //given
        Item item = new Item("ItemA",100, 10,null);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getItemId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void update() {
        //given
        Item item = new Item("ItemA", 100, 10,null);
        Item savedItem = itemRepository.save(item);
        UUID itemId = savedItem.getItemId();
        //when
        Item updateParam = new Item("ItemB", 200, 20,null);
        itemRepository.update(itemId,updateParam);
        //then
        Item modifiedItem = itemRepository.findById(itemId);
        assertThat(modifiedItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(modifiedItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(modifiedItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void deleteById() {
        //given
        Item itemA = new Item("itemA", 100, 1,null);
        Item itemB = new Item("itemB", 200, 2,null);
        Item savedItemA = itemRepository.save(itemA);
        itemRepository.save(itemB);
        //when
        itemRepository.deleteById(savedItemA.getItemId());
        //then
        List<Item> result = itemRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
    }
}