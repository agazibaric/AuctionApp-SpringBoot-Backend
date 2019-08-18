package com.agazibaric.service;

import com.agazibaric.dao.AuctionDao;
import com.agazibaric.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService implements IAuctionService {

    @Autowired
    private AuctionDao dao;

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        dao.findAll().forEach(items::add);
        return items;
    }

    @Override
    public Item getItemById(Long id) {
        Optional<Item> op = dao.findById(id);
        return op.isPresent() ? op.get() : null;
    }

    @Override
    public void addItem(Item item) {
        dao.save(item);
    }

    @Override
    public void deleteItemById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void updateItem(Long id, Item item) {

    }

}
