package com.agazibaric.service;

import com.agazibaric.entity.Item;

import java.util.Collection;

public interface IAuctionService {

    public Collection<Item> getAllItems();
    public Item getItemById(long id);
    public void addItem(Item item);
    public void deleteItemById(long id);
    public void updateItem(long id, Item item);

}
