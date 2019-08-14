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

    private static Map<Integer, Item> items;

    static {
        items = new HashMap<Integer, Item>() {
            {
                put(0, new Item(0, "Fender Standard Stratocaster", "1969", 1000f,
                        LocalDateTime.now(), Duration.ofDays(2)));
                put(1, new Item(1, "Gibson Les Paul", "1971", 1200f,
                        LocalDateTime.now(), Duration.ofDays(2)));
                put(2, new Item(2, "Fender Telecaster", "1968", 1100f,
                        LocalDateTime.now(), Duration.ofDays(2)));
            }
        };
    }

    @Override
    public Collection<Item> getAllItems() {
        return items.values();
    }

    @Override
    public Item getItemById(int id) {
        return items.get(id);
    }

    @Override
    public void addItem(Item item) {
        items.put(item.getId(), item);
    }

    @Override
    public void deleteItemById(int id) {
        items.remove(id);
    }

    @Override
    public void updateItem(Item item) {
        items.put(item.getId(), item);
    }

}
