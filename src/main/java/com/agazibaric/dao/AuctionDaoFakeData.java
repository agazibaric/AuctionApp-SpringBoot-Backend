package com.agazibaric.dao;

import com.agazibaric.entity.Item;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AuctionDaoFakeData implements IAuctionDao {

    private static Map<Long, Item> items;

    static {
        items = new HashMap<Long, Item>() {
            {
                put(0L, new Item(0L, "Fender Standard Stratocaster", "1969", 1000f,
                        LocalDateTime.now(), Duration.ofDays(2)));
                put(1L, new Item(1L, "Gibson Les Paul", "1971", 1200f,
                        LocalDateTime.now(), Duration.ofDays(2)));
                put(2L, new Item(2L, "Fender Telecaster", "1968", 1100f,
                        LocalDateTime.now(), Duration.ofDays(2)));
            }
        };
    }

    @Override
    public Collection<Item> getAllItems() {
        return items.values();
    }

    @Override
    public Item getItemById(long id) {
        return items.get(id);
    }

    @Override
    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    @Override
    public void deleteItemById(long id) {
        items.remove(id);
    }

    @Override
    public void updateItem(long id, Item item) {
        items.put(item.getId(), item);
    }

}
