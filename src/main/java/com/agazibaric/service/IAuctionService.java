package com.agazibaric.service;

import com.agazibaric.entity.Item;

import java.util.List;

public interface IAuctionService {

    public List<Item> getAllItems();
    public Item getItemById(Long id);
    public void addItem(Item item);
    public void deleteItemById(Long id);
    public void updateItem(Item item);

}
