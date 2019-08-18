package com.agazibaric.controller;

import com.agazibaric.entity.Item;
import com.agazibaric.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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

    @PostMapping
    public void addItem(@RequestBody Item item) {

    }

}
