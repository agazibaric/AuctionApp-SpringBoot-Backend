package com.agazibaric.controller;

import com.agazibaric.entity.Item;
import com.agazibaric.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping(value = "/auction")
public class AuctionController {

    @Autowired
    private IAuctionService service;

    @GetMapping
    public Collection<Item> getAllItems() {
        return service.getAllItems();
    }

    @GetMapping(value = "/{id}")
    public Item getItemById(@PathVariable("id") long id) {
        return service.getItemById(id);
    }

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(Item item) {
        System.out.println("...........................................");
        System.out.println(item.getId());
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
