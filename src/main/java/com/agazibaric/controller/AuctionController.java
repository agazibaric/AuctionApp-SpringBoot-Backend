package com.agazibaric.controller;

import com.agazibaric.entity.Item;
import com.agazibaric.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/items")
public class AuctionController {

    @Autowired
    private IAuctionService service;

    @GetMapping
    public List<Item> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping(value = "/{id}")
    public Item getItemById(@PathVariable("id") long id) {
        return service.getItemById(id);
    }

    @RequestMapping(method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestBody Item item) {
        item.setCreationTime(LocalDateTime.now());
        service.addItem(item);
    }

    @PutMapping(value = "/{id}")
    public void updateItem(@PathVariable("id") Long id, @RequestBody Item item) {
        service.updateItem(id, item);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@PathVariable long id) {
        service.deleteItemById(id);
    }

}
