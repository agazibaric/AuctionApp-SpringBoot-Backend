package com.agazibaric.controller;

import com.agazibaric.entity.Item;
import com.agazibaric.service.IAuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/auction")
public class AuctionController {

    @Autowired
    private IAuctionService service;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Item> getAllItems() {
        return service.getAllItems();
    }

}
