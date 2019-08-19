package com.agazibaric.item;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RepositoryEventHandler
public class ItemEventHandler {
     
    @HandleBeforeCreate
    public void handleAuthorBeforeCreate(Item item){
        item.setCreationTime(LocalDateTime.now());
    }

}
